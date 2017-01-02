package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 编制服务错误基础类
 * @author zhao
 *
 */
public class StaffServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3425712420915631660L;
	
	public static final StaffServiceException ADD_STAFF_FAIL = new StaffServiceException(10060001, "添加编制失败！");
	
	public static final StaffServiceException UPDATE_STAFF_FAIL = new StaffServiceException(10060002, "更新编制信息失败！");

	public StaffServiceException() {
		
	}
	
	public StaffServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public StaffServiceException(int code, String msg) {
		super(code, msg);
	}
}
