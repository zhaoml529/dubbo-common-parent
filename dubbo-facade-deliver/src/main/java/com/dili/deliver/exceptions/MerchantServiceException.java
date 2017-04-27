package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 商户服务错误基础类
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:31:40
 */
public class MerchantServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -473955666306221432L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final MerchantServiceException ADD_MERCHANT_ERR = new  MerchantServiceException(10120001, "添加失败！");
	
	public static final MerchantServiceException UPDATE_MERCHANT_ERR = new  MerchantServiceException(10120002, "修改失败！");
	
	public static final MerchantServiceException DELETE_MERCHANT_ERR = new  MerchantServiceException(10120003, "删除失败！");
	
	public MerchantServiceException() {
		
	}
	
	public MerchantServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public MerchantServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static MerchantServiceException create(int code, String msg) {
		return new MerchantServiceException(code, msg);
	}
	
}
