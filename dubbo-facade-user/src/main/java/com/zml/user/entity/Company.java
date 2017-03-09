package com.zml.user.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.zml.common.entity.BaseEntity;

/**
 * 组织架构-公司
 * @author zhao
 *
 */
public class Company extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8542729736868134131L;

	@NotBlank(message = "{company.name.not.blank}")
	private String name;		// 公司名称
	
	private String address;		// 公司地址
	
	private String phone;		// 公司电话
	
	private Integer status = 100;	// 100=正常 101=禁用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
