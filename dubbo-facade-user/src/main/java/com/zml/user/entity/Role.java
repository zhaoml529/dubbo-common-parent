package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 权限-角色(和岗位挂钩)
 * @author zhao
 *
 */
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -365859061449776757L;
	
	private String name;		// 角色名称
	
	private String roleType;	// 角色标识
	
	private Integer status = 100;	// 100=正常 101=禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
