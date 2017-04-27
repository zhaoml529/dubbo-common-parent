package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 订单服务错误基础类
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:24:48
 */
public class OrderServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 867646484988423321L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final OrderServiceException ADD_ORDER_ERR = new  OrderServiceException(10090001, "添加失败！");
	
	public static final OrderServiceException UPDATE_ORDER_ERR = new  OrderServiceException(10090002, "修改失败！");
	
	public static final OrderServiceException DELETE_ORDER_ERR = new  OrderServiceException(10090003, "删除失败！");
	
	public OrderServiceException() {
		
	}
	
	public OrderServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public OrderServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static OrderServiceException create(int code, String msg) {
		return new OrderServiceException(code, msg);
	}
	
}
