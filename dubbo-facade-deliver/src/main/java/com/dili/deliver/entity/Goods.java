package com.dili.deliver.entity;

import java.math.BigDecimal;

import com.zml.common.entity.BaseEntity;


/**
 * 商品表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:09:31
 */
public class Goods extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -831985070271263118L;
	
	private String goodsName;		// 商品名称
	private String goodsCode;		// 商品编码
	private String specification;	// 商品规格
	private String unit;			// 单位
	private BigDecimal unitPrice;	// 单价
	private Integer status;			// 状态(100=正常 101=禁用)

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	public String getGoodsCode() {
		return goodsCode;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public String getSpecification() {
		return specification;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
}
