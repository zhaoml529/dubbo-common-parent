package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

public class RoleAndResourceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062051321691433449L;

	public static final RoleAndResourceException ADD_RAR_FAIL = new RoleAndResourceException(10050001, "关联角色资源失败！");
	
	public static final RoleAndResourceException UPDATE_RAR_FAIL = new RoleAndResourceException(10050002, "更新角色资源信息失败！");

	public RoleAndResourceException() {
		
	}
	
	public RoleAndResourceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public RoleAndResourceException(int code, String msg) {
		super(code, msg);
	}
}
