package com.dili.deliver.entity;

import java.math.BigDecimal;

import com.zml.common.entity.BaseEntity;


/**
 * 订单商品表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:09:30
 */
public class OrderGoods extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5954101598432274547L;
	
	private String orderNum;		// 订单编号
	private String goodsType;		// 商品状态(1.原有商品 2.退货商品)
	private String goodsCode;		// 商品编码
	private Integer num;			// 购买、退货数量
	private BigDecimal totalPrice;	// 总价
	private Integer status;			// 状态(100=正常 101=禁用)

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	public String getGoodsCode() {
		return goodsCode;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Integer getNum() {
		return num;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
}
