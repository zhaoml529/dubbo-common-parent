package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 资源服务错误基础类
 * @author zhao
 *
 */
public class ResourceServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3755318545975147884L;

	public static final ResourceServiceException ADD_RESOURCE_ERR = new ResourceServiceException(10070001, "添加资源失败！");
	
	public static final ResourceServiceException UPDATE_RESOURCE_ERR = new ResourceServiceException(10070002, "更新资源信息失败！");

	public ResourceServiceException() {
		
	}
	
	public ResourceServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public ResourceServiceException(int code, String msg) {
		super(code, msg);
	}
}
