package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 组织架构-编制(java初级工程师1、java初级工程师2、java高级工程师1、产品经理1、产品经理2、前端工程师1、前端工程师2)
 * @author zhao
 *
 */
public class Staff extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8988301044345373155L;
	
	private String name;		// 编制名称
	
	private Long staffNum;		// 编号-唯一(可用作用户登录)
	
	private Long companyId;		// 所属公司
	
	private Long deptId;		// 所属部门

	private Long postId;		// 所属岗位		
	
	private Integer status = 100;	// 100=正常 101=禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(Long staffNum) {
		this.staffNum = staffNum;
	}
	
}
