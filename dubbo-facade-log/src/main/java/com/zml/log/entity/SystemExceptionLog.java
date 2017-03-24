package com.zml.log.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 系统异常日志 
 * @author zhaomingliang
 * @date 2017年3月23日
 */
public class SystemExceptionLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2064327607957482560L;

	private Long userId;

	private String userName;
	
	private Long staffNum;			// 编制号
	
	private String methodName;		// 请求方法
	
	private Integer errorCode;			// 异常代码
	
	private String errorName;		// 异常名称
	
	private String errorMessage;	// 异常信息
	
	private String params;			// 请求参数
	
	private String ip;				// ip地址
	
	private String content;			// 操作内容

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(Long staffNum) {
		this.staffNum = staffNum;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
