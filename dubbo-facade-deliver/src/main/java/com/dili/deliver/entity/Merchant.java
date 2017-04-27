package com.dili.deliver.entity;

import com.zml.common.entity.BaseEntity;


/**
 * 商户表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:18:27
 */
public class Merchant extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5305133618642029993L;
	
	private String name;		// 商户名称
	private Long areaId;		// 片区（站点）id
	private Long longitude;		// 经度
	private Long latitude;		// 纬度
	private String address;		// 详细地址
	private Integer status;		// 状态(100=正常 101=禁用)


	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
}
