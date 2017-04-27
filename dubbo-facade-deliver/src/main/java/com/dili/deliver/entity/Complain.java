package com.dili.deliver.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.zml.common.entity.BaseEntity;


/**
 * 投诉表
 * @description: 
 * @author: zhengrs
 * @date: 2017年4月19日 下午1:20:34
 */
public class Complain extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632346625770286144L;
	@NotBlank(message = "{complain.source.not.blank}")
	private Integer source;		// 投诉来源（1.公司服务电话 2.站点服务电话）
	
	@NotBlank(message = "{complain.complainTime.not.blank}")
	private Date complainTime;	// 投诉时间
	
	@NotBlank(message = "{complain.areaId.not.blank}")
	private Long areaId;		// 对应的片区(站点)id
	
	@NotBlank(message = "{complain.merchantId.not.blank}")
	private Long merchantId;	// 商户id
	
	private String orderNum;	// 订单编号
	
	@NotBlank(message = "{complain.content.not.blank}")
	private String content;		// 投诉内容
	
	private String resolve;		//处理备注
	
	@NotBlank(message = "{complain.result.not.blank}")
	private String result;		//处理结果（1.已处理 2.未处理）
	
	private Integer status;		// 状态(100=正常 101=禁用)
	
	private String merchantName;// 商户名称(关联查询)
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Date getComplainTime() {
		return complainTime;
	}
	public void setComplainTime(Date complainTime) {
		this.complainTime = complainTime;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResolve() {
		return resolve;
	}
	public void setResolve(String resolve) {
		this.resolve = resolve;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getMerchantName() {
		return merchantName;
	}

}
