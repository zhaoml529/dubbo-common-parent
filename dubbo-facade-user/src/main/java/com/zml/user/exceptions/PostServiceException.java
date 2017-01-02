package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 岗位服务错误基础类
 * @author zhao
 *
 */
public class PostServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7384571092010938604L;

	public static final PostServiceException ADD_POST_FAIL = new PostServiceException(10040001, "添加岗位失败！");
	
	public static final PostServiceException UPDATE_POST_FAIL = new PostServiceException(10040002, "更新岗位信息失败！");

	public PostServiceException() {
		
	}
	
	public PostServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public PostServiceException(int code, String msg) {
		super(code, msg);
	}

}
