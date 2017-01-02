package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 部门服务错误基础类
 * @author zhao
 *
 */
public class DepartmentServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3625549386686584875L;
	
	public static final DepartmentServiceException ADD_DEPT_FAIL = new DepartmentServiceException(10030001, "添加部门失败！");
	
	public static final DepartmentServiceException UPDATE_DEPT_FAIL = new DepartmentServiceException(10030002, "更新部门信息失败！");
	
	public DepartmentServiceException() {
		
	}
	
	public DepartmentServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public DepartmentServiceException(int code, String msg) {
		super(code, msg);
	}

}
