package com.zml.activiti.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 评论
 * @author ZML
 *
 */

public class Comments extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6302533824139455741L;

	private String userId = "";			//评论人id
	
	private String userName = "";		//评论人
	
	private String content = "";		//评论内容
	
	private String processInstanceId = "";	//流程id
	
	private Long businessKey = 0L;		//业务id
	
	/**
	 * @see BusinessType
	 */
	private String businessType = ""; 	//业务类型
	
	public Comments() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(Long businessKey) {
		this.businessKey = businessKey;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}
