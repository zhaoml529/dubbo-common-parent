package com.zml.base.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 17:54:41
 */
public class DistrictServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7647724776743112603L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final DistrictServiceException ADD_DISTRICT_ERR = new  DistrictServiceException(10020001, "添加失败！");
	
	public static final DistrictServiceException UPDATE_DISTRICT_ERR = new  DistrictServiceException(10020002, "修改失败！");
	
	public static final DistrictServiceException DELETE_DISTRICT_ERR = new  DistrictServiceException(10020003, "删除失败！");
	
	public DistrictServiceException() {
		
	}
	
	public DistrictServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public DistrictServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static DistrictServiceException create(int code, String msg) {
		return new DistrictServiceException(code, msg);
	}
	
}
