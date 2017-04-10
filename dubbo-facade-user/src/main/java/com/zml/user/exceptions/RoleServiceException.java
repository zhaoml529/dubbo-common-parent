package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 角色服务错误基础类
 * @author zhao
 *
 */
public class RoleServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044630569061516565L;
	
	public static final RoleServiceException ADD_ROLE_ERR = new RoleServiceException(10050001, "添加角色失败！");
	
	public static final RoleServiceException UPDATE_ROLE_ERR = new RoleServiceException(10050002, "更新角色信息失败！");

	public RoleServiceException() {
		
	}
	
	public RoleServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public RoleServiceException(int code, String msg) {
		super(code, msg);
	}

}
