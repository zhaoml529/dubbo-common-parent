package com.zml.activiti.entity;

import com.zml.common.entity.BaseEntity;

/**
 * @Description 流程模型
 * @author zhaomingliang
 * @date 2016年9月1日
 */
public class ProcessModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5299586590828309845L;

	private String processType = "";		// 工作流类型(数据字典表维护)
	
	private String processName = "";		// 流程名称
	
	private String processKey = "";			// 启动流程的key;不同的流程不能有相同的key值
	
	private String deploymentId = "";		// 部署id(对应activiti中的deployment表中的id)
	
	private Integer suspensionState = 1;	// 流程状态 1=激活状态; 2=挂起状态 
	
	private String description = "";		// 描述 
	
	private String deployDate = "";			// 最后一次部署时间
	
	private Long creatorId = 0L;			// 创建人
	
	private String status = "1";			// 状态(1-正常 2-失效)
	
	private String remark = "";				// 备注
	
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public Integer getSuspensionState() {
		return suspensionState;
	}

	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeployDate() {
		return deployDate;
	}

	public void setDeployDate(String deployDate) {
		this.deployDate = deployDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

}
