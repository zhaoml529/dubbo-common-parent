package com.dili.deliver.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 竞争对手
 * @author FUPING
 *
 */
public class ComPetitor  extends BaseEntity {
	private static final long serialVersionUID = -2464346329124447792L;
	private String name;
	private String merchantName;
	private String remark;
	private Integer status;			// 状态(100=正常 101=禁用)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
	@Override
	public String toString() {
		return "ComPetitor [name=" + name + ", merchantName=" + merchantName + ", remark=" + remark + ", status="
				+ status + ", getId()=" + getId() + ", getVersion()=" + getVersion() + ", getCreateDate()="
				+ getCreateDate() + "]";
	}
	
}
