package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

public class UserServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1203720255300239670L;
	
	/**
	 * 登录：用户名不存在
	 */
	public static final int LOGIN_LOGNAME_NOT_EXIST = 10010001;
	/**
	 * 登录：密码错误
	 */
	public static final int LOGIN_PWD_ERROR = 10010002;

	/**
	 * 登录：密码错误次数超限
	 */
	public static final int LOGIN_PWDERRORTIMES_OVERRUN = 10010003;
	
	/**
	 * 登陆：身份验证失败
	 */
	public static final int LOGIN_AUTHENTICATION_FAIL = 10010004;

	/**
	 * 登录：账号被锁定
	 */
	public static final int LOGIN_USER_INACTIVE = 10010005;

	/**
	 * 登录：操作员被销户
	 */
	public static final int LOGIN_OPERATORSTATUS_CANCELLATION = 10010006;

	/**
	 * 用户已存在
	 */
	public static final int USERINFO_IS_EXIST = 10010007;

	/**
	 * 用户不存在
	 */
	public static final int USERINFO_NOT_EXIST = 10010008;
	
	/**
	 * 用户id不存在
	 */
	public static final int USER_ID_NOT_EXIST = 10010009;
	
	/**
	 * 更新用户失败
	 */
	public static final int UPDATE_USER_FAIL = 10010010;
	
	public UserServiceException() {
		
	}
	
	public UserServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public UserServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static UserServiceException create(String msg, int code) {
		return new UserServiceException(code, msg);
	}
}
