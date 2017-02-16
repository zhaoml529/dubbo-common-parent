package com.zml.user.entity;

import java.util.Date;

import com.zml.common.entity.BaseEntity;

public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2643312501176930880L;

	private String userName;
	
	private Long staffNum;		// 编制号
	
	private String passwd;
	
	private String salt;
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date registerDate = new Date();
	
	private Integer isLock = 100;	// 100=正常 101=锁定
	
	private Integer status = 100;	// 100=正常 101=禁用
	
	private Integer pwdErrorCount;	// 登录密码错误次数(5次锁定)
	
	private Date lastLoginTime;		// 最后登录时间
	
	private Date pwdErrorLastTime;	// 最后一次登录密码错误时间
	
	private Date lastUpdatePwdTime;	// 最后一次修改时间 

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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPwdErrorCount() {
		return pwdErrorCount;
	}

	public void setPwdErrorCount(Integer pwdErrorCount) {
		this.pwdErrorCount = pwdErrorCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getPwdErrorLastTime() {
		return pwdErrorLastTime;
	}

	public void setPwdErrorLastTime(Date pwdErrorLastTime) {
		this.pwdErrorLastTime = pwdErrorLastTime;
	}

	public Date getLastUpdatePwdTime() {
		return lastUpdatePwdTime;
	}

	public void setLastUpdatePwdTime(Date lastUpdatePwdTime) {
		this.lastUpdatePwdTime = lastUpdatePwdTime;
	}

}
