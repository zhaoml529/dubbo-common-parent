package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:24:49
 */
public class OrderGoodsServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8321811279102030195L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final OrderGoodsServiceException ADD_ORDER_GOODS_ERR = new  OrderGoodsServiceException(10090010, "添加失败！");
	
	public static final OrderGoodsServiceException UPDATE_ORDER_GOODS_ERR = new  OrderGoodsServiceException(10090011, "修改失败！");
	
	public static final OrderGoodsServiceException DELETE_ORDER_GOODS_ERR = new  OrderGoodsServiceException(10090012, "删除失败！");
	
	public OrderGoodsServiceException() {
		
	}
	
	public OrderGoodsServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public OrderGoodsServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static OrderGoodsServiceException create(int code, String msg) {
		return new OrderGoodsServiceException(code, msg);
	}
	
}
