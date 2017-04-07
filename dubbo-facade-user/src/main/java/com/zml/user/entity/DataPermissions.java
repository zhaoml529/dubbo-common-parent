package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 数据权限组(角色对应的数据权限集合)
 * 1.如果查看部分岗位数据，则只有岗位字段有数据
 * 2.如果查看多个部们所有岗位数据，和另一个部门部分岗位数据，则岗位字段和部门字段都有数据(岗位 or 部门的所有数据)。
 * 3.如果查看公司下所有部门和岗位的数据，则只有公司字段有数据
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
	
	private Long postId;	// 所选岗位id(多个用逗号隔开)
	
	private Long deptId;	// 所选部门id(多个用逗号隔开)
	
	private Long companyId;	// 所选公司id(多个用逗号隔开)
}
