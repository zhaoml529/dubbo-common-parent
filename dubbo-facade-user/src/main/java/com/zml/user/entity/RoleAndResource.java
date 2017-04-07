package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 角色功能点(角色对应的功能点集合)
 * @author zhao
 *
 */
public class RoleAndResource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5047487763328937509L;

	private Long roleId;
	
	private Long resourceId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
}
