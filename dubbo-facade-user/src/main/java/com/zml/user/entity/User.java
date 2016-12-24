package com.zml.user.entity;

import java.util.Date;

import com.zml.common.entity.BaseEntity;

public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2643312501176930880L;

	private String userName;
	
	private String staffId;
	
	private String passwd;
	
	private String salt;
	
	private Date registerDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
	
}
