package com.dili.deliver.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.zml.common.entity.BaseEntity;


/**
 * 订单表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:09:29
 */
public class Order extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667471686848337044L;
	
	private String orderNum;		// 订单编号
	private Long merchantId;		// 商户id
	private String payType;			// 支付类型(1.线上支付 2.货到付款)
	private String payWay;			// 付款类型（1.支付宝 2.微信 3.现金）
	private Date payDate;			// 支付时间
	private Date inputDate;			// 录入时间
	private Date outDate;			// 出库时间
	private Date claimData;			// 签收时间
	private Long userId;			// 送货员id
	private BigDecimal totalPrice;	// 订单总金额
	private String isUrgent;		// 是否加急(1.是 2.否)
	private BigDecimal alreadReceive;		// 已收款
	private BigDecimal notReceive;	// 未收款
	private String orderType;		// 订单状态（1=已录入 2=已出库(配送中) 3=签收未收款 4=签收已收款 5=拒签收）
	private String remark;			// 备注
	private String reason;			// 拒签原因
	private Integer status;			// 状态(100=正常 101=禁用)

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	public Long getMerchantId() {
		return merchantId;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getPayType() {
		return payType;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	public String getPayWay() {
		return payWay;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public Date getPayDate() {
		return payDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	
	public Date getInputDate() {
		return inputDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	public Date getOutDate() {
		return outDate;
	}

	public void setClaimData(Date claimData) {
		this.claimData = claimData;
	}
	
	public Date getClaimData() {
		return claimData;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}
	
	public String getIsUrgent() {
		return isUrgent;
	}

	public void setAlreadReceive(BigDecimal alreadReceive) {
		this.alreadReceive = alreadReceive;
	}
	
	public BigDecimal getAlreadReceive() {
		return alreadReceive;
	}

	public void setNotReceive(BigDecimal notReceive) {
		this.notReceive = notReceive;
	}
	
	public BigDecimal getNotReceive() {
		return notReceive;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getOrderType() {
		return orderType;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
}
