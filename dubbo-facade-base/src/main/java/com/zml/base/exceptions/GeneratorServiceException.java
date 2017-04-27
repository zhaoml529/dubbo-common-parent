package com.zml.base.exceptions;

import com.zml.common.exceptions.ServiceException;

public class GeneratorServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5638609044067112069L;
	
	public static final int XUAN_RAN_ERR = 80010001;
	
	public static final GeneratorServiceException GET_CONFIG_ERR = new GeneratorServiceException(80010002, "获取模版配置文件失败！");

	public GeneratorServiceException() {
		
	}
	
	public GeneratorServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public GeneratorServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static GeneratorServiceException create(String msg, int code) {
		return new GeneratorServiceException(code, msg);
	}

}
