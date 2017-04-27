package com.zml.base.entity;

import com.zml.common.entity.BaseEntity;


/**
 * 省市区表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 17:54:41
 */
public class District extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2393884608003230902L;
	
	private String districtName;		// 省市区名称
	private String districtCode;		// 区域编码
	private String parentCode;			// 父编码
	private String districtType;		// 区域类型（1--省 2--直辖市 3--自治区 4--特别行政区）
	private Integer status;				// 状态(100=正常 101=禁用)


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	public String getDistrictCode() {
		return districtCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	public String getParentCode() {
		return parentCode;
	}

	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}
	
	public String getDistrictType() {
		return districtType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
}
