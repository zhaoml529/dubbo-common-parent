package com.dili.deliver.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zml.common.entity.BaseEntity;


/**
 * 片区表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:48:11
 */
public class Area extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3165662282467834659L;
	
	@NotBlank(message = "{area.siteName.not.blank}")
	private String siteName;		// 站点名称
	
	@NotBlank(message = "{area.ssqCode.not.blank}")
	private String provinceCode;	// 省级code
	
	@NotBlank(message = "{area.ssqCode.not.blank}")
	private String cityCode;		// 市级code
	
	@NotBlank(message = "{area.ssqCode.not.blank}")
	private String districtCode;	// 区级code
	
	private String managerName;		// 站长名称(关联查询)
	
	@NotBlank(message = "{area.managerId.not.blank}")
	private String managerId;		// 站长id
	
	@NotBlank(message = "{area.delivery.not.blank}")
	private String userIds;			// 送货员id(多个逗号隔开)
	
	private List<AreaDeliveryUser> deliverUser;	// 送货员
	
	private Integer status;			// 状态(100=正常 101=禁用)

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public String getSiteName() {
		return siteName;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCityCode() {
		return cityCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	public String getDistrictCode() {
		return districtCode;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public List<AreaDeliveryUser> getDeliverUser() {
		return deliverUser;
	}

	public void setDeliverUser(List<AreaDeliveryUser> deliverUser) {
		this.deliverUser = deliverUser;
	}
	
}
