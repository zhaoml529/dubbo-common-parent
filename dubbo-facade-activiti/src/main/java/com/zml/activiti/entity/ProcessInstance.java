package com.zml.activiti.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 流程实例
 * @author zhaomingliang
 * @date 2017年3月30日
 */
public class ProcessInstance extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733681175688424733L;

	private Long modelId = 0L;
	
	private Long procDefId = 0L;
	
	private String operationType = "1"; // 操作类型 1.同意 2.不同意 3.重新申请 4.取消申请
	
	private Long targetRef = 0L;		// 指向节点的id, 如果指向的是结束节点则为0
	
	private Long creatorId = 0L;		//创建人
	
	private String remark = "";			//备注

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(Long procDefId) {
		this.procDefId = procDefId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Long getTargetRef() {
		return targetRef;
	}

	public void setTargetRef(Long targetRef) {
		this.targetRef = targetRef;
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
	
}
