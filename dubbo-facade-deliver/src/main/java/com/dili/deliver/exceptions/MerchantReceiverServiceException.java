package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:31:41
 */
public class MerchantReceiverServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3655731369380839867L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final MerchantReceiverServiceException ADD_MERCHANT_RECEIVER_ERR = new  MerchantReceiverServiceException(10120010, "添加失败！");
	
	public static final MerchantReceiverServiceException UPDATE_MERCHANT_RECEIVER_ERR = new  MerchantReceiverServiceException(10120011, "修改失败！");
	
	public static final MerchantReceiverServiceException DELETE_MERCHANT_RECEIVER_ERR = new  MerchantReceiverServiceException(10120012, "删除失败！");
	
	public MerchantReceiverServiceException() {
		
	}
	
	public MerchantReceiverServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public MerchantReceiverServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static MerchantReceiverServiceException create(int code, String msg) {
		return new MerchantReceiverServiceException(code, msg);
	}
	
}
