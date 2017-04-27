package com.dili.deliver.entity;

import com.zml.common.entity.BaseEntity;


/**
 * 商户收货人关联表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:18:28
 */
public class MerchantReceiver extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8224197846065690222L;
	
	private Long merchantId;	// 商户id
	private String name;		// 收货人名称
	private String phone;		// 电话
	private Integer status;		// 状态(100=正常 101=禁用)

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
}
