package com.zml.user.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zml.common.entity.BaseEntity;

public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2643312501176930880L;

	@NotBlank(message = "{user.name.not.blank}")
	private String userName;
	
	
	private String realName;
	
	@NotNull(message = "{user.staffNum.not.null}")
	private Long staffNum;			// 用户编号(唯一)
	
	@NotBlank(message = "{user.passwd.not.blank}")
	@Length(min = 8, max = 15, message = "{user.passwd.length.illegal}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message="{user.passwd.illegal}")
	private String passwd;
	
	@JsonIgnore
	private String salt;
	
	private Long companyId;			// 公司id
	
	private Long deptId;			// 部门id
	
	private Long postId;			// 岗位id		
	
	private Long roleId;			// 所属角色
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date registerDate = new Date();
	
	private Integer isLock = 100;	// 100=正常 101=锁定
	
	
	private Integer status = 100;	// 100=正常 101=禁用
	
	private Date lastLoginTime;		// 最后登录时间
	
	private Date pwdErrorLastTime;	// 最后一次登录密码错误时间
	
	private Date lastUpdatePwdTime;	// 最后一次修改密码时间 
	
	public User(){
		
	}
	
	public User(String userName) {
		this.userName = userName;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}
