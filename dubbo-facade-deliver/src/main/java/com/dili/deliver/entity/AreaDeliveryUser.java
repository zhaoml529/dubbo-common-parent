package com.dili.deliver.entity;

import com.zml.common.entity.BaseEntity;


/**
 * 片区送货员
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:48:12
 */
public class AreaDeliveryUser extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3999168569909005525L;
	
	private Long areaId;			// 对应的片区(站点)id
	private Long userId;			// 用户(送货员)id
	private Long userName;			// 送货员姓名
	private Integer status = 100;	// 状态(100=正常 101=禁用)

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	
	public Long getAreaId() {
		return areaId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}

	public Long getUserName() {
		return userName;
	}

	public void setUserName(Long userName) {
		this.userName = userName;
	}
	
}
