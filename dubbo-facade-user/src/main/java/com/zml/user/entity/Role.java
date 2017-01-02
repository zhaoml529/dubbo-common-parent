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
	
	private String name;	// 角色名称
	
	private Long postId;	// 岗位
	
	private String logo;	// 角色标识
	
	private Integer status = 100;	// 100=正常 101=禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
