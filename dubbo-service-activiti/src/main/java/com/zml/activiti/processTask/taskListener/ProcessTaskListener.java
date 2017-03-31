package com.zml.activiti.processTask.taskListener;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zml.activiti.entity.ProcessTask;
import com.zml.activiti.service.IProcessTaskService;

/**
 * 动态更新事项表(ProcessTask)中的 taskId等信息
 * @author ZML
 */
@Component("processTaskListener")
public class ProcessTaskListener implements TaskListener {

	private static final long serialVersionUID = 2200736398161972540L;
	
	@Autowired
	private IProcessTaskService processTaskService;
	
    @Autowired
    protected RepositoryService repositoryService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		String processTaskId = delegateTask.getVariable("processTaskId").toString();			// 用户待办任务id
		Long applyStaffId = Long.valueOf(delegateTask.getVariable("startStaffId").toString());	// 申请人编制id
		try {
			if(StringUtils.isNotBlank(processTaskId)){
				String processDefinitionId = delegateTask.getProcessDefinitionId();		// processTask1:8:30012
				ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
				String processDefinitionName = processDefinition.getName();				// 分析报告审批流程
				ProcessTask processTask = this.processTaskService.findById(Long.valueOf(processTaskId));
				processTask.setBusinessType(processDefinitionName);						// 业务类型(审批流程配置中的 工作流类型名称)
				processTask.setTaskId(delegateTask.getId());							// 任务Id
				processTask.setExecutionId(delegateTask.getExecutionId());				// 执行流Id
				processTask.setProcessInstanceId(delegateTask.getProcessInstanceId());	// 流程实例Id
				processTask.setAssign(delegateTask.getAssignee());						// 任务处理人
				processTask.setOwner(delegateTask.getOwner());							// 任务拥有人
				processTask.setApplyStaffId(applyStaffId);								// 申请人编制id
				this.processTaskService.update(processTask);
				delegateTask.setVariable("title", processTask.getTitle());				// 按title查询代办任务用
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
