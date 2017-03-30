package com.zml.activiti.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.activiti.entity.Comments;
import com.zml.activiti.entity.ProcessTask;
import com.zml.activiti.exceptions.ProcessServiceException;
import com.zml.activiti.service.IProcessService;
import com.zml.activiti.service.IProcessTaskService;
import com.zml.common.page.Page;
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
	public Page findTodoTask(Parameter<ProcessTask> param) throws ProcessServiceException {
		param.initPage();
		Map<String, Object> map = param.getParamMap();
		String staffId = map.get("staffId").toString();	// 用户编制id
		String businessType = map.get("businessType").toString();
		TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(staffId);
 		if(StringUtils.isNotBlank(businessType)) {
 			taskQuery.processVariableValueLike("businessType", "%"+businessType+"%");
 		}
 		Integer totalSum = taskQuery.list().size();
		Page page = new Page(param.getCurrPage(), param.getNumPage(), totalSum, Collections.emptyList());
		//查询代办
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(page.getBeginPageIndex(), page.getEndPageIndex());
		List<ProcessTask> taskList = getUserTaskList(tasks);
		//page.setRecordList(taskList);
		return page;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delegateTask(String userId, String taskId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferTask(Long userId, String taskId, String processTaskId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getVariableByExecutionId(String executionId,
			String variableName) throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVariableByExecutionId(String executionId,
			String variableName) throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveVariableByExecutionId(String executionId,
			String variableName, Object object) throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProcessInstance getProcessInstanceById(String processInstanceId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessDefinition getProcessDefinitionById(String processDefinitionId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveTo(String currentTaskId, String targetTaskDefinitionKey)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTo(TaskEntity currentTaskEntity,
			String targetTaskDefinitionKey) throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream getDiagram(String processInstanceId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getDiagramByProInstanceId_noTrace(String resourceType,
			String processInstanceId) throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getDiagramByProDefinitionId_noTrace(String resourceType,
			String processDefinitionId) throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcessTask> findFinishedProcessInstances(
			Parameter<ProcessTask> param) throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcessTask> findFinishedTaskInstances(String userId,
			Parameter<ProcessTask> param) throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcessInstance> listRuningProcess(Parameter<ProcessTask> param)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateProcessDefinition(String processDefinitionId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspendProcessDefinition(String processDefinitionId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateProcessInstance(String processInstanceId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspendProcessInstance(String processInstanceId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Task getTaskByProcessInstanceId(String processInstanceId)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStaffToActiviti(Map<String, Object> params)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStaffToActiviti(Map<String, Object> params)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteActivitiUser(Map<String, Object> params)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPostToActiviti(Map<String, Object> params)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteActivitiGroup(Map<String, Object> params)
			throws ProcessServiceException {
		// TODO Auto-generated method stub
		
	}

}
