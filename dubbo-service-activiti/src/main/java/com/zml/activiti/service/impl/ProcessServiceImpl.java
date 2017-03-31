package com.zml.activiti.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.activiti.entity.Comments;
import com.zml.activiti.entity.ProcessTask;
import com.zml.activiti.exceptions.ProcessServiceException;
import com.zml.activiti.processTask.taskCommand.DeleteActiveTaskCmd;
import com.zml.activiti.processTask.taskCommand.StartActivityCmd;
import com.zml.activiti.service.IProcessService;
import com.zml.activiti.service.IProcessTaskService;
import com.zml.common.page.Parameter;

@Service("processService")
public class ProcessServiceImpl implements IProcessService {
	
	private  static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl. class);

	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected RepositoryService repositoryService;
    
    @Autowired
    protected HistoryService historyService;
    
	@Autowired
	protected ProcessEngine processEngine;
    
    
/*    @Autowired
    protected ProcessEngineFactoryBean processEngineFactory;*/

    @Autowired
    protected ProcessEngineConfiguration processEngineConfiguration;
    
	@Autowired
	private IProcessTaskService processTaskService;
	
	@Override
	public String startProcess(String startKey, String startUserId,
			String businessKey, Map<String, Object> variables)
			throws ProcessServiceException {
		String processInstanceId = null;
		try{
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中。对应ACT_HI_PROCINST 中的START_USER_ID_
			this.identityService.setAuthenticatedUserId(startUserId);
			ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(startKey, businessKey, variables);
			processInstanceId = processInstance.getId();
		} catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.error("没有部署流程，请在[流程定义]中部署相应流程文件！", e);
            } else {
                logger.error("启动流程失败，系统内部错误！", e);
            }
            throw e;
        } catch (Exception e) {
            logger.error("启动流程失败：", e);
            throw e;
        }finally{
			this.identityService.setAuthenticatedUserId(null);
		}
        return processInstanceId;
	}

	@Override
	public List<ProcessTask> findTodoTask(Parameter<ProcessTask> param) throws ProcessServiceException {
		Map<String, Object> map = param.getParamMap();
		String staffId = map.get("staffId").toString();	// 用户编制id
		String businessType = map.get("businessType").toString();
		TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(staffId);
 		if(StringUtils.isNotBlank(businessType)) {
 			taskQuery.processVariableValueLike("businessType", "%"+businessType+"%");
 		}
 		//Integer totalSum = taskQuery.list().size();
		/*Page page = new Page(param.getCurrPage(), param.getNumPage(), totalSum, Collections.emptyList());*/
		
		param.initPage();
		int[] pageParams = param.getPageParam().getPageParams();
		
		//查询代办
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(pageParams[0], pageParams[1]);
		List<ProcessTask> taskList = getUserTaskList(tasks);
		return taskList;
	}
	
	 /**
     * 将Task集合转为ProcessTask集合
     * @param tasks
     * @return
     */
    protected List<ProcessTask> getUserTaskList(List<Task> tasks) throws ProcessServiceException{
    	List<ProcessTask> taskList = new ArrayList<ProcessTask>();
        for (Task task : tasks) {
        	String processInstanceId = task.getProcessInstanceId();
        	String executionId = task.getExecutionId();
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if(processInstance == null){
            	continue;//如果有挂起的流程则continue
            }
            String processTaskId = getVariableByExecutionId(executionId, "processTaskId").toString();
            if(StringUtils.isNotBlank(processTaskId)) {
            	ProcessTask processTask = this.processTaskService.findById(Long.valueOf(processTaskId));
            	processTask.setProcessDefinitionId(processInstance.getProcessDefinitionId());
            	processTask.setTaskName(task.getName());
            	processTask.setTaskDefinitionKey(task.getTaskDefinitionKey());
            	ProcessDefinition processDefinition = getProcessDefinitionById(processInstance.getProcessDefinitionId());
            	processTask.setProcessDefinitionKey(processDefinition.getKey());
            	processTask.setSupended(processInstance.isSuspended());
            	processTask.setModelVersion(processDefinition.getVersion());
            	taskList.add(processTask);
            }
        }
    	return taskList;
    }

	@Override
	public void claim(String staffId, String taskId)
			throws ProcessServiceException {
		this.taskService.claim(taskId, staffId);
        this.processTaskService.claimTask(taskId, staffId);
		
	}

	@Override
	public void delegateTask(String userId, String taskId)
			throws ProcessServiceException {
		//API: If no owner is set on the task, the owner is set to the current assignee of the task.
		//OWNER_（委托人）：受理人委托其他人操作该TASK的时候，受理人就成了委托人OWNER_，其他人就成了受理人ASSIGNEE_
		//assignee容易理解，主要是owner字段容易误解，owner字段就是用于受理人委托别人操作的时候运用的字段
		this.taskService.delegateTask(taskId, userId);
		
	}

	@Override
	public void transferTask(Long userId, String taskId, String processTaskId)
			throws ProcessServiceException {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task != null){
			if(StringUtils.isNotBlank(processTaskId)) {
				ProcessTask processTask = this.processTaskService.findById(Long.valueOf(processTaskId));
				String assign = processTask.getAssign();
				processTask.setOwner(assign);
				processTask.setAssign(userId.toString());
				this.processTaskService.update(processTask);
			}
			
			String assign = task.getAssignee();
			this.taskService.setAssignee(taskId, userId.toString());
			this.taskService.setOwner(taskId, assign);
		}else{
			throw new ActivitiObjectNotFoundException("任务不存在！", this.getClass());
		}
		
	}

	@Override
	public String complete(Map<String, Object> params)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer revoke(String historyTaskId, String processInstanceId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comments> getComments(String processInstanceId)
			throws ProcessServiceException {
		// 查询一个任务所在流程的全部评论
		List<Comment> comments = this.taskService.getProcessInstanceComments(processInstanceId);
		List<Comments> commnetList = new ArrayList<Comments>();
		for(Comment comment : comments){
			Comments co = new Comments();
			co.setContent(comment.getFullMessage());
			co.setCreateDate(comment.getTime());
			co.setUserName("");
			commnetList.add(co);
		}
    	return commnetList;
	}

	@Override
	public Object getVariableByExecutionId(String executionId,
			String variableName) throws ProcessServiceException {
		return this.runtimeService.getVariableLocal(executionId, variableName);
	}

	@Override
	public void deleteVariableByExecutionId(String executionId,
			String variableName) throws ProcessServiceException {
		this.runtimeService.removeVariable(executionId, variableName);
		
	}

	/**
	 * 根据executionId保存流程参数
	 */
	@Override
	public void saveVariableByExecutionId(String executionId,
			String variableName, Object object) throws ProcessServiceException {
		this.runtimeService.setVariable(executionId, variableName, object);
		
	}

	@Override
	public ProcessInstance getProcessInstanceById(String processInstanceId)
			throws ProcessServiceException {
		return this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	}

	@Override
	public ProcessDefinition getProcessDefinitionById(String processDefinitionId)
			throws ProcessServiceException {
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
	}

	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 */
	@Override
	public void moveTo(String currentTaskId, String targetTaskDefinitionKey)
			throws ProcessServiceException {
		TaskEntity taskEntity = (TaskEntity) this.taskService.createTaskQuery().taskId(currentTaskId).singleResult();
		moveTo(taskEntity, targetTaskDefinitionKey);
		
	}

	@Override
	public void moveTo(TaskEntity currentTaskEntity,
			String targetTaskDefinitionKey) throws ProcessServiceException {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(currentTaskEntity.getProcessDefinitionId()); 
		//PvmActivity是部署时期对象，ActivityImpl是它的实现类
		ActivityImpl activity = (ActivityImpl) pde.findActivity(targetTaskDefinitionKey);

		moveTo(currentTaskEntity, activity);
		
	}
	
	private void moveTo(TaskEntity currentTaskEntity, ActivityImpl activity)
	{
		Command<Void> deleteCmd = new DeleteActiveTaskCmd(currentTaskEntity, "jump", true);
		Command<Void> StartCmd = new StartActivityCmd(currentTaskEntity.getExecutionId(), activity);
		this.processEngine.getManagementService().executeCommand(deleteCmd);
		this.processEngine.getManagementService().executeCommand(StartCmd);
	}

	/**
     * 显示流程图-带流程跟踪
     * @param resourceType
     * @param processInstanceId
     * @return
     */
	@Override
	public InputStream getDiagram(String processInstanceId)
			throws ProcessServiceException {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        
        
        ProcessDiagramGenerator procDiaGenerator = new DefaultProcessDiagramGenerator();
        InputStream imageStream = procDiaGenerator.generateDiagram(  
                bpmnModel,   
                "png",   
                activeActivityIds,   
                new ArrayList<String>(),   
                "宋体",   
                "宋体",   
                null,   
                1.0);  
    	return imageStream;
		
	}

    /**
     * 显示流程图-通过流程实例ID，，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     */
	@Override
	public InputStream getDiagramByProInstanceId_noTrace(String resourceType,
			String processInstanceId) throws ProcessServiceException {
		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId())
                .singleResult();

        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
	}

	/**
     * 显示流程图-通过流程定义ID，不带流程跟踪(没有乱码啊问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     * @throws Exception
     */
	@Override
	public InputStream getDiagramByProDefinitionId_noTrace(String resourceType,
			String processDefinitionId) throws ProcessServiceException {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = this.repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
	}

	/**
     * 读取已结束的流程(admin流程管理中查看)
     */
	@Override
	public List<ProcessTask> findFinishedProcessInstances(
			Parameter<ProcessTask> param) throws ProcessServiceException {
		param.initPage();
		int[] pageParams = param.getPageParam().getPageParams();
		
        HistoricProcessInstanceQuery historQuery = this.historyService.createHistoricProcessInstanceQuery().finished();
		List<HistoricProcessInstance> list = historQuery.orderByProcessInstanceEndTime().desc().listPage(pageParams[0], pageParams[1]);
		List<ProcessTask> processList = new ArrayList<ProcessTask>();
		
		for (HistoricProcessInstance historicProcessInstance : list) {
			String processInstanceId = historicProcessInstance.getId();
			List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "processTask".equals(var.getVariableName())){
					ProcessTask base = (ProcessTask) var.getValue();
//					base.setHistoricProcessInstance(historicProcessInstance);
//					base.setProcessDefinition(getProcessDefinitionById(historicProcessInstance.getProcessDefinitionId()));
					processList.add(base);
					break;
				}
			}
		}
		
        return processList;
	}

	/**
     * 用户查看已完成任务
     */
	@Override
	public List<ProcessTask> findFinishedTaskInstances(String userId,
			Parameter<ProcessTask> param) throws ProcessServiceException {
		param.initPage();
		int[] pageParams = param.getPageParam().getPageParams();
		HistoricTaskInstanceQuery historQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).finished();
    	List<HistoricTaskInstance> list = historQuery.orderByHistoricTaskInstanceEndTime().desc().listPage(pageParams[0], pageParams[1]);
    	List<ProcessTask> taskList = new ArrayList<ProcessTask>();
    	
    	for(HistoricTaskInstance historicTaskInstance : list) {
    		String taskId = historicTaskInstance.getId();
    		ProcessTask processTask = this.processTaskService.findByTaskId(taskId);
    		taskList.add(processTask);
    	}
		return taskList;
	}

	@Override
	public List<ProcessInstance> listRuningProcess(Parameter<ProcessTask> param)
			throws ProcessServiceException {
		param.initPage();
		int[] pageParams = param.getPageParam().getPageParams();
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> list = processInstanceQuery.orderByProcessInstanceId().desc().listPage(pageParams[0], pageParams[1]);
		return list;
	}

	@Override
	public void activateProcessDefinition(String processDefinitionId)
			throws ProcessServiceException {
		this.repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
		
	}

	@Override
	public void suspendProcessDefinition(String processDefinitionId)
			throws ProcessServiceException {
		this.repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
		
	}

	@Override
	public void activateProcessInstance(String processInstanceId)
			throws ProcessServiceException {
		this.runtimeService.activateProcessInstanceById(processInstanceId);
		
	}

	@Override
	public void suspendProcessInstance(String processInstanceId)
			throws ProcessServiceException {
		this.runtimeService.suspendProcessInstanceById(processInstanceId);
		
	}

	@Override
	public Task getTaskByProcessInstanceId(String processInstanceId)
			throws ProcessServiceException {
		if(StringUtils.isNotBlank(processInstanceId)) {
			return this.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		} else {
			return null;
		}
	}

	@Override
	public Boolean delDeploy(String deploymentId, Boolean cascade)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getProcessDefineList(
			Map<String, Object> params) throws ProcessServiceException {
		ProcessDefinitionQuery processDefinitionQuery = this.repositoryService.createProcessDefinitionQuery();
		if(params.get("deploymentId") != null) {
			processDefinitionQuery = processDefinitionQuery.deploymentId(params.get("deploymentId").toString());
		}
		if(params.get("deploymentId") != null) {
			processDefinitionQuery = processDefinitionQuery.processDefinitionKey(params.get("processDefinitionKey").toString());
		}
		if(params.get("deploymentId") != null) {
			processDefinitionQuery = processDefinitionQuery.processDefinitionName(params.get("processDefinitionName").toString());
		}
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
    		Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    		Map<String, Object> map=new HashMap<String, Object>();
    		map.put("processDefinitionId", processDefinition.getId());
    		map.put("name", processDefinition.getName());
    		map.put("key", processDefinition.getKey());
    		map.put("deploymentId", processDefinition.getDeploymentId());
    		map.put("version", processDefinition.getVersion());
    		map.put("resourceName", processDefinition.getResourceName());
    		map.put("diagramResourceName", processDefinition.getDiagramResourceName());
    		map.put("deploymentTime", deployment.getDeploymentTime());
    		map.put("suspended", processDefinition.isSuspended());
    		list.add(map);
		}
		return list;
	}

	@Override
	public void addStaffToActiviti(Map<String, Object> params)
			throws ProcessServiceException {
		UserQuery userQuery = this.identityService.createUserQuery();
		List<User> activitiUsers = userQuery.userId(params.get("staffId").toString()).list();
		if (activitiUsers.size() >= 1) {
			logger.error("发现重复编制，不能同步 staffId="+params.get("staffId").toString());
		} else {
			newActivitiUser(params);
		}
	}
	
	private void newActivitiUser(Map<String, Object> params) {
		String staffId = params.get("staffId").toString();
		String postId = params.get("postId").toString();
		if(StringUtils.isNotBlank(postId)) {
			// 添加用户
        	saveActivitiUser(params);
        	// 添加membership
        	addMembershipToIdentify(staffId, postId);
		} else {
			logger.error("postId 为空不能同步membership");
		}
	}
	
	/**
     * 添加一个用户到Activiti {@link org.activiti.engine.identity.User}
     * @param params  用户对象
     */
    private void saveActivitiUser(Map<String, Object> params) {
        String staffId = params.get("staffId").toString();
        User activitiUser = identityService.newUser(staffId);
        cloneAndSaveActivitiUser(params, activitiUser);
        logger.info("add activiti user: {}"+ToStringBuilder.reflectionToString(activitiUser));
    }
    
    /**
     * 使用系统用户对象属性设置到Activiti User对象中
     * @param user          系统用户对象
     * @param activitiUser  Activiti User
     */
    private void cloneAndSaveActivitiUser(Map<String, Object> params, User activitiUser) {
        activitiUser.setFirstName(params.get("staffName").toString());
        activitiUser.setLastName(StringUtils.EMPTY);
        activitiUser.setPassword(StringUtils.EMPTY);
        activitiUser.setEmail(StringUtils.EMPTY);
        this.identityService.saveUser(activitiUser);
    }
    
    /**
     * 添加Activiti Identify的用户于组关系
     * @param staffId   编制id
     * @param postId    岗位id
     */
    private void addMembershipToIdentify(String staffId, String postId) {
        this.identityService.createMembership(staffId, postId);
    }

	@Override
	public void updateStaffToActiviti(Map<String, Object> params)
			throws ProcessServiceException {
		String staffId = params.get("staffId").toString();
		String postId = params.get("postId").toString();
		//this.activitiIdentityService.updateMembership(staffId, postId);
		
	}

	@Override
	public void deleteActivitiUser(Map<String, Object> params)
			throws ProcessServiceException {
		String staffId = params.get("staffId").toString();
		this.identityService.deleteUser(staffId);	// 同时删除membership
	}

	@Override
	public void addPostToActiviti(Map<String, Object> params)
			throws ProcessServiceException {
		String postId = params.get("postId").toString();
		String postName = params.get("postName").toString();
		Group group = this.identityService.newGroup(postId);
		group.setName(postName);
        this.identityService.saveGroup(group);
	}

	@Override
	public void deleteActivitiGroup(Map<String, Object> params)
			throws ProcessServiceException {
		String postId = params.get("postId").toString();
		this.identityService.deleteGroup(postId);	// 同时删除membership
	}

}
