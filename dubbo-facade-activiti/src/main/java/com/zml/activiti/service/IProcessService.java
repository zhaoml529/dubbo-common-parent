package com.zml.activiti.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.zml.activiti.entity.Comments;
import com.zml.activiti.entity.ProcessTask;
import com.zml.activiti.exceptions.ProcessServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


public interface IProcessService {
	
	/**
	 * 启动流程
	 * @param startKey	
	 * @param startUserId	
	 * @param businessKey
	 * @param variables
	 * @throws ProcessServiceException
	 */
	public String startProcess(String startKey, String startUserId, String businessKey, Map<String, Object> variables) throws ProcessServiceException;
	
	/**
	 * 查询代办任务
	 * @param currentPage
	 * @param pageSize
	 * @param params
	 * @return
	 * @throws ProcessServiceException
	 */
	public Page findTodoTask(Parameter<ProcessTask> param) throws ProcessServiceException;
	
    /**
     * 签收任务
     * @param staffId
     * @param taskId
     * @throws ProcessServiceException
     */
    public void claim(String staffId, String taskId) throws ProcessServiceException;
    
    /**
     * 委派任务
     * @param userId
     * @param taskId
     * @throws ProcessServiceException
     */
    public void delegateTask(String userId, String taskId) throws ProcessServiceException;
    
    /**
     * 转办任务
     * @param userId
     * @param taskId
     * @param processTaskId
     * @throws ProcessServiceException
     */
    public void transferTask(Long userId, String taskId, String processTaskId) throws ProcessServiceException;
    
    /**
     * 完成任务
     * @param taskId  			任务ID
     * @param userId  			当前登陆用户id
     * @param comments			评论内容
     * @param variables			任务参数	
     * @throws ProcessServiceException	
     */
    public String complete(Map<String, Object> params) throws ProcessServiceException;
    
    /**
     * 撤销任务
     * @param historyTaskId
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public Integer revoke(String historyTaskId, String processInstanceId) throws ProcessServiceException;
    
    /**
     * 获取评论
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public List<Comments> getComments(String processInstanceId) throws ProcessServiceException;
    
    /**
     * 根据executionId获取流程参数 
     * @param executionId
     * @param variableName
     * @throws ProcessServiceException
     */
    public Object getVariableByExecutionId(String executionId, String variableName) throws ProcessServiceException;
    
    /**
     * 根据executionId移除流程参数
     * @param executionId
     * @param variableName
     * @throws ProcessServiceException
     */
    public void deleteVariableByExecutionId(String executionId, String variableName) throws ProcessServiceException;
    
    /**
     * 根据executionId保存流程参数
     * @param executionId
     * @param variableName
     * @param object
     * @throws ProcessServiceException
     */
    public void saveVariableByExecutionId(String executionId, String variableName, Object object) throws ProcessServiceException;
    
    /**
     * 根据processInstanceId获取流程实例
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public ProcessInstance getProcessInstanceById(String processInstanceId) throws ProcessServiceException;
    
    /**
     * 根据processDefinitionId获取流程定义实体
     * @param processDefinitionId
     * @return
     * @throws ProcessServiceException
     */
    public ProcessDefinition getProcessDefinitionById(String processDefinitionId) throws ProcessServiceException;
    
	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 * @param currentTaskEntity			当前任务节点
	 * @param targetTaskDefinitionKey	目标任务节点（在模型定义里面的节点名称）
	 * @throws ProcessServiceException
	 */
	public void moveTo(String currentTaskId, String targetTaskDefinitionKey) throws ProcessServiceException;

	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 * 
	 * @param currentTaskEntity			当前任务节点
	 * @param targetTaskDefinitionKey	目标任务节点（在模型定义里面的节点名称）
	 * @throws ProcessServiceException
	 */
	public void moveTo(TaskEntity currentTaskEntity, String targetTaskDefinitionKey) throws ProcessServiceException;
 
    
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public InputStream getDiagram(String processInstanceId) throws ProcessServiceException;
    
    /**
     * 显示图片-通过流程ID，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public InputStream getDiagramByProInstanceId_noTrace(String resourceType, String processInstanceId) throws ProcessServiceException;
    
    /**
     * 显示图片-通过部署ID，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public InputStream getDiagramByProDefinitionId_noTrace(String resourceType, String processDefinitionId) throws ProcessServiceException;

    /**
     * 读取已结束中的流程-admin查看
     * @param page
     * @throws ProcessServiceException
     */
    public List<ProcessTask> findFinishedProcessInstances(Parameter<ProcessTask> param) throws ProcessServiceException;
    
    /**
     * 各个审批人员查看自己完成的任务
     * @param user
     * @param page
     * @throws ProcessServiceException
     */
    public List<ProcessTask> findFinishedTaskInstances(String userId, Parameter<ProcessTask> param) throws ProcessServiceException;
    
    /**
     * 管理运行中流程
     * @param page
     * @throws ProcessServiceException
     */
    public List<ProcessInstance> listRuningProcess(Parameter<ProcessTask> param) throws ProcessServiceException;

    /**
     * 激活流程定义
     * @param processDefinitionId
     * @throws ProcessServiceException
     */
    public void activateProcessDefinition(String processDefinitionId) throws ProcessServiceException;
    
    /**
     * 挂起流程定义
     * @param processDefinitionId
     * @throws ProcessServiceException
     */
    public void suspendProcessDefinition(String processDefinitionId) throws ProcessServiceException;
    
    
    /**
     * 激活流程实例
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public void activateProcessInstance(String processInstanceId) throws ProcessServiceException;
    
    /**
     * 挂起流程实例
     * @param processInstanceId
     * @throws ProcessServiceException
     */
    public void suspendProcessInstance(String processInstanceId) throws ProcessServiceException;
    
    /**
     * 通过processInstanceId获取当前任务
     * @param processInstanceId
     * @return
     * @throws ProcessServiceException
     */
    public Task getTaskByProcessInstanceId(String processInstanceId) throws ProcessServiceException;
    
    /**
     * admin级别权限
     * 删除部署的流程，级联删除流程实例 true。
     * 不管是否指定级联删除，部署的相关数据均会被删除，这些数据包括流程定义的身份数据（IdentityLink）、流程定义数据（ProcessDefinition）、流程资源（Resource）
     * 部署数据（Deployment）。
     * 如果设置级联(true)，则会删除流程实例数据（ProcessInstance）,其中流程实例也包括流程任务（Task）与流程实例的历史数据；如果设置flase 将不会级联删除。
     * 如果数据库中已经存在流程实例数据，那么将会删除失败，因为在删除流程定义时，流程定义数据的ID已经被流程实例的相关数据所引用。
     * 
     * 删除失败时，是ACT_RU_IDENTITYLINK中TASK_ID有值，ACT_RU_TASK 无法删除
     *
     * @param deploymentId 流程部署ID
     * @param cascade 是否级联删除
     */
    public Boolean delDeploy(String deploymentId, Boolean cascade) throws ProcessServiceException;
    
    /**
     * 查询流程定义列表
     * @param params
     * @return
     * @throws ProcessServiceException
     */
    public List<Map<String, Object>> getProcessDefineList(Map<String, Object> params) throws ProcessServiceException; 
    
    /**
     * 同步用户编制信息
     * @param params
     * @throws ProcessServiceException
     */
    public void addStaffToActiviti(Map<String, Object> params) throws ProcessServiceException;
    
    /**
     * 同步用户编制信息
     * @param params
     * @throws ProcessServiceException
     */
    public void updateStaffToActiviti(Map<String, Object> params) throws ProcessServiceException;
    
    /**
     * 删除Act中的用户编制信息
     * @param params
     * @throws ProcessServiceException
     */
    public void deleteActivitiUser(Map<String, Object> params) throws ProcessServiceException;
    
    /**
     * 同步岗位信息
     * @param params
     * @throws ProcessServiceException
     */
    public void addPostToActiviti(Map<String, Object> params) throws ProcessServiceException;
    
    /**
     * 同步删除Act中的岗位信息
     * @param params
     * @throws ProcessServiceException
     */
    public void deleteActivitiGroup(Map<String, Object> params) throws ProcessServiceException;
    
}
