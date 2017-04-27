package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 商品服务错误基础类
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:24:50
 */
public class GoodsServiceException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4455392869330463626L;

	/**
	 * 错误编码需要根据实际编码规则修改
	 */
	public static final GoodsServiceException ADD_GOODS_ERR = new  GoodsServiceException(10100001, "添加失败！");
	
	public static final GoodsServiceException UPDATE_GOODS_ERR = new  GoodsServiceException(10100002, "修改失败！");
	
	public static final GoodsServiceException DELETE_GOODS_ERR = new  GoodsServiceException(10100003, "删除失败！");
	
	public GoodsServiceException() {
		
	}
	
	public GoodsServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public GoodsServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static GoodsServiceException create(int code, String msg) {
		return new GoodsServiceException(code, msg);
	}
	
}
