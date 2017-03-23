package com.zml.log.entity;

import com.zml.common.entity.BaseEntity;


/**
 * 用户操作日志
 * @Description
 * @author zhaomingliang
 * @date 2017年2月16日
 */
public class UserOperateLog extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4745047557662541623L;
	
	private Long userId;

	private String userName;
	
	private Long staffNum;			// 编制号
	
	private String methodName;		// 请求方法
	
	private String ip;				// ip地址
	
	private String content;			// 操作内容
	
	private String operType; 		// 操作类型 ADD-增加，UPDATE-修改，DELETE-删除，QUERY-查询，LOGIN-登录，LOGOUT-登出	

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

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
