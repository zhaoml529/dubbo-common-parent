package com.zml.log.exceptions;

import com.zml.common.exceptions.ServiceException;

public class UserOperateServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595270482065400713L;

	public static final UserOperateServiceException ADD_OPERATE_FAIL = new UserOperateServiceException(80080001, "添加用户操作日志失败！");
	
	public static final UserOperateServiceException UPDATE_OPERATE_FAIL = new UserOperateServiceException(80080002, "更新用户操作日志失败！");

	public UserOperateServiceException() {
		
	}
	
	public UserOperateServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public UserOperateServiceException(int code, String msg) {
		super(code, msg);
	}
}
