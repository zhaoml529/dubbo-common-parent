package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;


/**
 * 投诉服务错误基础类
 * @description: 
 * @author: zhengrs
 * @date: 2017年4月19日 下午1:46:18
 */
public class ComplainServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2494265752733590568L;

	
	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final ComplainServiceException ADD_COMPLAIN_ERR = new  ComplainServiceException(10100001, "添加失败！");
	
	public static final ComplainServiceException UPDATE_COMPLAIN_ERR = new  ComplainServiceException(10100002, "修改失败！");
	
	public static final ComplainServiceException DELETE_COMPLAIN_ERR = new  ComplainServiceException(10100003, "删除失败！");
	
	public ComplainServiceException() {
		
	}
	
	public ComplainServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public ComplainServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static ComplainServiceException create(int code, String msg) {
		return new ComplainServiceException(code, msg);
	}
}
