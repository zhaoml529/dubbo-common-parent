package com.zml.activiti.processTask.taskCommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zml.common.utils.SpringContextUtil;


/**
 * Activiti 命令拦截器 Command
 * 撤回任务
 * @author ZML
 *
 */

@Component
public class RevokeTaskCmd implements Command<Integer> {

	private static final Logger logger = LoggerFactory.getLogger(RevokeTaskCmd.class);
	
    private HistoryService historyService;
	
    private RuntimeService runtimeService;

    private TaskService taskService;
    
	private String historyTaskId;
	
	private String processInstanceId;
	
	public RevokeTaskCmd(){
		
	}
	
	public RevokeTaskCmd(String historyTaskId, String processInstanceId){
		this.historyTaskId = historyTaskId;
		this.processInstanceId = processInstanceId;
	}
	
	/**
	 * 0-撤销成功 1-流程结束 2-下一结点已经通过,不能撤销
	 * @param historyTaskId
	 * @param processInstanceId
	 * @return
	 */
	@Override
	public Integer execute(CommandContext commandContext) {
		this.runtimeService = commandContext.getProcessEngineConfiguration().getRuntimeService();
		this.taskService = commandContext.getProcessEngineConfiguration().getTaskService();
		this.historyService = commandContext.getProcessEngineConfiguration().getHistoryService();
		//获得历史任务
    	HistoricTaskInstanceEntity historicTaskInstanceEntity = Context
                .getCommandContext()
                .getHistoricTaskInstanceEntityManager()
                .findHistoricTaskInstanceById(historyTaskId);
    	// 获得历史节点
        HistoricActivityInstanceEntity historicActivityInstanceEntity = getHistoricActivityInstanceEntity(historyTaskId);
    	
        //获取当前节点
        TaskEntity currentTask;
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(processInstance != null){
        	currentTask = this.getCurrentTaskEntity(processInstance);
        	HistoricTaskInstance hti = this.historyService.createHistoricTaskInstanceQuery().taskId(currentTask.getId()).singleResult();
        	
        	//下一结点已经通过,不能撤销。
        	if("completed".equals(hti.getDeleteReason())){
        		logger.info("cannot revoke {}", historyTaskId);
        		return 2;
        	}
        }else{
        	return 1;
        }
        // 删除所有活动中的task
//        this.deleteActiveTasks(processInstance.getProcessInstanceId());
        Command<Void> cmd = new DeleteActiveTaskCmd(currentTask, "revoke", true);
        
        Context.getProcessEngineConfiguration().getManagementService().executeCommand(cmd);
        
        this.deleteHistoryActivities(this.historyTaskId, this.processInstanceId);
        // 恢复期望撤销的任务和历史
        this.processHistoryTask(historicTaskInstanceEntity,
        		historicActivityInstanceEntity);

        logger.info("activiti is revoke by {}", historicTaskInstanceEntity.getName());

        return 0;
	}

	/**
	 * 获取当前节点信息
	 * @param processInstanceId
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private TaskEntity getCurrentTaskEntity(ProcessInstance processInstance) {
		TaskEntity currentTask = null;
		try {
			String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
			currentTask = (TaskEntity) this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId).singleResult();
		} catch (Exception e) {
            logger.error("can not get property activityId from processInstance: {}", processInstance);
        }
		return currentTask;
	}
	
	/**
	 * 获得历史任务节点
	 * @param historyTaskId
	 * @return
	 */
    public HistoricActivityInstanceEntity getHistoricActivityInstanceEntity(
            String historyTaskId) {
    	JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate");
        String historicActivityInstanceId = jdbcTemplate.queryForObject(
                "select id_ from ACT_HI_ACTINST where task_id_=?",
                String.class, historyTaskId);
        logger.info("historicActivityInstanceId : {}",historicActivityInstanceId);

        HistoricActivityInstanceQueryImpl historicActivityInstanceQueryImpl = new HistoricActivityInstanceQueryImpl();
        historicActivityInstanceQueryImpl.activityInstanceId(historicActivityInstanceId);

        HistoricActivityInstanceEntity historicActivityInstanceEntity = (HistoricActivityInstanceEntity) Context
                .getCommandContext()
                .getHistoricActivityInstanceEntityManager()
                .findHistoricActivityInstancesByQueryCriteria(
                        historicActivityInstanceQueryImpl, new Page(0, 1))
                .get(0);
        return historicActivityInstanceEntity;
    }
    
    /**
     * 删除未完成任务.
     */
    public void deleteActiveTasks(String processInstanceId) {
        Context.getCommandContext().getTaskEntityManager()
                .deleteTasksByProcessInstanceId(processInstanceId, "revoke", true);
        
    }
    
    /**
     * 删除历史节点.
     */
    public void deleteHistoryActivities(String historyTaskId, String processInstanceId) {
    	JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate");
    	List<HistoricActivityInstance> list = this.historyService.createHistoricActivityInstanceQuery().unfinished().processInstanceId(processInstanceId).list();
    	
    	//删除历史评论（删除回退人的评论，保留其他人的评论）
    	Context.getCommandContext().getCommentEntityManager().deleteCommentsByTaskId(historyTaskId);
    	
    	//List<Comment> comments= Context.getCommandContext().getCommentEntityManager().findCommentsByTaskId(historyTaskId);
    	
    	/*User user = UserUtil.getUserFromSession();
    	for(Comment comment : comments) {
    		String userId = comment.getUserId();
    		String revokeUserId = user.getId().toString();
    		if(revokeUserId.equals(userId)) {
    			this.taskService.deleteComment(comment.getId());
    		}
    	}*/
    	
    	for(HistoricActivityInstance hai : list){
    		String taskId = hai.getTaskId();
    		if(taskId != null && !taskId.equals(historyTaskId)){
    			//删除历史任务
    			/*Context.getCommandContext()
                .getHistoricTaskInstanceEntityManager()
                .deleteHistoricTaskInstanceById(taskId);*/
    			this.historyService.deleteHistoricTaskInstance(taskId);
    			
    			//删除历史行为
    			jdbcTemplate.update("delete from ACT_HI_ACTINST where task_id_=?", taskId);
    		}
    		
    	}
    }
    
    /**
     * 恢复任务
     * @param historicTaskInstanceEntity
     * @param historicActivityInstanceEntity
     */
    public void processHistoryTask(
            HistoricTaskInstanceEntity historicTaskInstanceEntity,
            HistoricActivityInstanceEntity historicActivityInstanceEntity) {
        historicTaskInstanceEntity.setEndTime(null);
        historicTaskInstanceEntity.setDurationInMillis(null);
        historicActivityInstanceEntity.setEndTime(null);
        historicActivityInstanceEntity.setDurationInMillis(null);
        
        TaskEntity task = TaskEntity.create(new Date());
        task.setProcessDefinitionId(historicTaskInstanceEntity.getProcessDefinitionId());
        task.setId(historicTaskInstanceEntity.getId());
        task.setAssigneeWithoutCascade(historicTaskInstanceEntity.getAssignee());
        task.setParentTaskIdWithoutCascade(historicTaskInstanceEntity.getParentTaskId());
        task.setNameWithoutCascade(historicTaskInstanceEntity.getName());
        task.setTaskDefinitionKey(historicTaskInstanceEntity.getTaskDefinitionKey());
        task.setExecutionId(historicTaskInstanceEntity.getExecutionId());
        task.setPriority(historicTaskInstanceEntity.getPriority());
        task.setProcessInstanceId(historicTaskInstanceEntity.getProcessInstanceId());
        task.setDescriptionWithoutCascade(historicTaskInstanceEntity.getDescription());

        Context.getCommandContext().getTaskEntityManager().insert(task);

        // 把流程指向任务对应的节点
        ExecutionEntity executionEntity = Context.getCommandContext()
                .getExecutionEntityManager()
                .findExecutionById(historicTaskInstanceEntity.getExecutionId());
        executionEntity.setActivity(getActivity(historicActivityInstanceEntity));
    }
    
    public ActivityImpl getActivity(
            HistoricActivityInstance historicActivityInstanceEntity) {
        ProcessDefinitionEntity processDefinitionEntity = new GetDeploymentProcessDefinitionCmd(
                historicActivityInstanceEntity.getProcessDefinitionId())
                .execute(Context.getCommandContext());

        return processDefinitionEntity
                .findActivity(historicActivityInstanceEntity.getActivityId());
    }

}
