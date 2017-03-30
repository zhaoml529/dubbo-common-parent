package com.zml.activiti.entity;

import org.activiti.engine.history.HistoricTaskInstance;

import com.zml.common.entity.BaseEntity;

/**
 * 待办任务
 * @author zhaomingliang
 */

public class ProcessTask extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1834880751490641959L;

	private Long processTaskId = 0L;
	
	private Long applyStaffId = 0L;			// 申请人编制id
	
	private String applyUserName = "";		// 申请人姓名
	
	private String title = "";				// 申请标题
	
	private String url = "";				// 待处理任务的url
	
	private String businessType = "";		// 业务类型(ProcessModel.processName)
	
	private Long businessKey = 0L;			// 业务id
	
	private String taskId = "";				// 任务id
	
	private String executionId = "";		// 执行流id
	
	private String processInstanceId = "";	// 流程实例id
	
	private Integer status = 0;				// 任务状态是否完成   0：未完成，1：已完成
	
	private String assign = "";				// 任务处理人(编制id)
	
	private String assignName = "";			// 处理人姓名
	
	private String owner = "";				// 任务执行人(编制id)
	
	private String ownerName = "";			// 执行人姓名
	
	private Integer operationType = 0; 		// 操作类型 1.同意 2.不同意 3.重新申请 4.取消申请 5.完成任务
	
	private String completeDate = "";		// 完成时间

	private String taskName = "";
	
	private String taskDefinitionKey = "";
	
	private String processDefinitionId = "";
	
	private String processDefinitionKey = "";
	
	private HistoricTaskInstance historicTaskInstance;
	
	private Boolean supended = false;		// 是否挂起
	
	private Integer modelVersion = 0;			// 流程版本号
	
	private Long createTime = System.currentTimeMillis();// 创建时间
	
	private Long creatorId = 0L;			// 创建人
	
	private String remark = "";				// 备注
	
	
    public ProcessTask() {
    	
    }

	public Long getProcessTaskId() {
		return processTaskId;
	}

	public void setProcessTaskId(Long processTaskId) {
		this.processTaskId = processTaskId;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Long getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(Long businessKey) {
		this.businessKey = businessKey;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public HistoricTaskInstance getHistoricTaskInstance() {
		return historicTaskInstance;
	}

	public void setHistoricTaskInstance(HistoricTaskInstance historicTaskInstance) {
		this.historicTaskInstance = historicTaskInstance;
	}

	public Boolean getSupended() {
		return supended;
	}

	public void setSupended(Boolean supended) {
		this.supended = supended;
	}


	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public Long getApplyStaffId() {
		return applyStaffId;
	}

	public void setApplyStaffId(Long applyStaffId) {
		this.applyStaffId = applyStaffId;
	}

	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

}
