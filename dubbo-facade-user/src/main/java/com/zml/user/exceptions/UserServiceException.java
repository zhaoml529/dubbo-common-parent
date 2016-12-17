package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

public class UserServiceException extends ServiceException {

	/***
	 * 登录：用户名不存在
	 */
	public static final int LOGIN_LOGNAME_NOT_EXIST = 20020001;
	/***
	 * 登录：密码错误
	 */
	public static final int LOGIN_PWD_ERROR = 20020002;

	/***
	 * 登录：密码错误次数超限
	 */
	public static final int LOGIN_PWDERRORTIMES_OVERRUN = 20020003;

	/***
	 * 登录：操作员状态为冻结
	 */
	public static final int LOGIN_OPERATORSTATUS_INACTIVE = 20020004;
	
	/***
	 * 登录：操作员被销户
	 */
	public static final int LOGIN_OPERATORSTATUS_CANCELLATION = 20020005;

	/***
	 * 用户已存在
	 */
	public static final int USERINFO_IS_EXIST = 20020006;

	/***
	 * 用户不存在
	 */
	public static final int USERINFO_NOT_EXIST = 20020007;
	
	public UserServiceException() {
		
	}
	
	public UserServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public UserServiceException(int code, String msg) {
		super(code, msg);
	}
}
