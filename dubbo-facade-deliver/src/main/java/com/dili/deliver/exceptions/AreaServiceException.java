package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 片区(站点)服务错误基础类
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:48:11
 */
public class AreaServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2558135972044588174L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final AreaServiceException ADD_AREA_ERR = new  AreaServiceException(10110001, "添加失败！");
	
	public static final AreaServiceException UPDATE_AREA_ERR = new  AreaServiceException(10110002, "修改失败！");
	
	public static final AreaServiceException DELETE_AREA_ERR = new  AreaServiceException(10110003, "删除失败！");
	
	public static final AreaServiceException QUERY_AREA_WITH_SSQ_ERR = new  AreaServiceException(10110004, "通过省市区查询站点失败！");
	
	public static final AreaServiceException AREA_NOT_EXIST = new  AreaServiceException(10110004, "片区不存在！");
	
	public AreaServiceException() {
		
	}
	
	public AreaServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public AreaServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static AreaServiceException create(int code, String msg) {
		return new AreaServiceException(code, msg);
	}
	
}
