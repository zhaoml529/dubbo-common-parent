package com.zml.activiti.entity;

import java.util.List;

import com.zml.common.entity.BaseEntity;

/**
 * 流程定义
 * @author zhaomingliang
 * @date 2017年3月30日
 */
public class ProcessDefine extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2907074862223825648L;

	private Long modelId = 0L;			// 对应流程模型id
	
	private String taskId = "";			// 节点id
	
	private String taskName = "";		// 节点名称
	
	private Integer candidateType = 1;	// 操作者类型(1=自定义；2=处理人(编制)；3=候选组(岗位)；4=不指定，由发起人处理； 5=候选人(多个编制))
	
	private String candidateIds = "";	// 操作人/候选人/候选部门/发起人(人员编制id或岗位id,多个用逗号隔开；其中发起人可留空或设置专有标识)
	
	private Integer isStartEvent = 0;	// 是否是开始节点 0=不是 1=是
	
	private Integer isInitiator = 0;	// 此任务节点是否由发起人处理 0=否 1=是
	
	private String targetGateway = "";	// 指向关口的名称
	
	private Long creatorId = 0L;		//创建人
	
	private String remark = "";			//备注
	
	private List<ProcessInstance> processInstanceList;

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(Integer candidateType) {
		this.candidateType = candidateType;
	}

	public String getCandidateIds() {
		return candidateIds;
	}

	public void setCandidateIds(String candidateIds) {
		this.candidateIds = candidateIds;
	}

	public Integer getIsStartEvent() {
		return isStartEvent;
	}

	public void setIsStartEvent(Integer isStartEvent) {
		this.isStartEvent = isStartEvent;
	}

	public Integer getIsInitiator() {
		return isInitiator;
	}

	public void setIsInitiator(Integer isInitiator) {
		this.isInitiator = isInitiator;
	}

	public String getTargetGateway() {
		return targetGateway;
	}

	public void setTargetGateway(String targetGateway) {
		this.targetGateway = targetGateway;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ProcessInstance> getProcessInstanceList() {
		return processInstanceList;
	}

	public void setProcessInstanceList(List<ProcessInstance> processInstanceList) {
		this.processInstanceList = processInstanceList;
	}
	
}
