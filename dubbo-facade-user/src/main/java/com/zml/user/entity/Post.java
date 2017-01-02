package com.zml.user.entity;

import com.zml.common.entity.BaseEntity;
/**
 * 组织架构-岗位(Java初级工程师岗、Java高级工程师岗、产品经理岗、前端工程师岗)
 * @author zhao
 *
 */
public class Post extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7270875389382603837L;
	
	private String name;		// 岗位名称
	
	private Long companyId;	// 所属公司
	
	private Long deptId;		// 所属部门
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
