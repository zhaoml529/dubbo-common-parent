package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 数据权限组(角色对应的数据权限集合)
 * 岗位、部门、公司字段有且只有一个字段有值，想查看哪级下的数据，对应字段存储相应的id值，多个逗号隔开
 * @author zhaomingliang
 * @date 2017年4月7日
 */
public class DataPermissions extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8830633179574954152L;

	private String name;	// 数据权限组名称
	
	private Long roleId;	// 角色id
	
	private Long postId;	// 要查看岗位下的数据(多个用逗号隔开)
	
	private Long deptId;	// 要查看部门下的数据(多个用逗号隔开)
	
	private Long companyId;	// 要查看公司下的数据(多个用逗号隔开)
	
	private Integer allData;// 是否查看所有数据(100=是 101=否)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getAllData() {
		return allData;
	}

	public void setAllData(Integer allData) {
		this.allData = allData;
	}

}
