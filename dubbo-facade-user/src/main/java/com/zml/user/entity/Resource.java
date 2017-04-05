package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 权限-资源表
 * @author zhao
 *
 */
public class Resource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4124484528843914429L;
	
	 private String name;		// 资源名称
	 
	 private String type;		// 资源类型(menu\button)
	 
	 private String url;		// 资源路径
	 
	 private String permission; // 权限字符串
	 
	 private Long parentId;		// 父id
	 
	 private Integer sort;		// 排序
	 
	 private String remark;		// 说明
	 
	 private Integer status = 100;	// 100=正常 101=禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	 
}
