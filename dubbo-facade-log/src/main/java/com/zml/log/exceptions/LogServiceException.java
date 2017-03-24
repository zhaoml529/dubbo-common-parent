package com.zml.log.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 系统日志日常
 * 用户操作日志 和 系统异常日志
 * @Description
 * @author zhaomingliang
 * @date 2017年3月23日
 */
public class LogServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595270482065400713L;

	public static final LogServiceException ADD_OPERATE_FAIL = new LogServiceException(80080001, "添加系统日志失败！");
	
	public static final LogServiceException UPDATE_OPERATE_FAIL = new LogServiceException(80080002, "更新系统日志失败！");

	public LogServiceException() {
		
	}
	
	public LogServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public LogServiceException(int code, String msg) {
		super(code, msg);
	}
}
