package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:48:12
 */
public class AreaDeliveryUserServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4565658890780094304L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final AreaDeliveryUserServiceException ADD_AREA_DELIVERY_USER_ERR = new  AreaDeliveryUserServiceException(10110010, "添加失败！");
	
	public static final AreaDeliveryUserServiceException UPDATE_AREA_DELIVERY_USER_ERR = new  AreaDeliveryUserServiceException(10110011, "修改失败！");
	
	public static final AreaDeliveryUserServiceException DELETE_AREA_DELIVERY_USER_ERR = new  AreaDeliveryUserServiceException(10110012, "删除失败！");
	
	public AreaDeliveryUserServiceException() {
		
	}
	
	public AreaDeliveryUserServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public AreaDeliveryUserServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static AreaDeliveryUserServiceException create(int code, String msg) {
		return new AreaDeliveryUserServiceException(code, msg);
	}
	
}
