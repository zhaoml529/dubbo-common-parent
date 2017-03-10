package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 公司服务错误基础类
 * @author zhao
 *
 */
public class CompanyServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7108468744058530206L;
	//private static final Log log = LogFactory.getLog(CompanyServiceException.class);
	
	/**
	 * 添加公司失败
	 */
	public static final CompanyServiceException ADD_COMPANY_FAIL = new CompanyServiceException(10020001, "添加公司失败！");
	
	/**
	 * 更新公司信息失败
	 */
	public static final CompanyServiceException UPDATE_COMPANY_FAIL = new CompanyServiceException(10020002, "更新公司信息失败！");
	
	public static final CompanyServiceException COMPANY_IS_EXIST = new CompanyServiceException(10020003, "未找到相应的公司信息！");
	
	public static final CompanyServiceException COMPANY_NOT_EXIST = new CompanyServiceException(10020004, "未找到相应的公司信息！");
	
	public static final CompanyServiceException DELETE_COMPANY_FAIL = new CompanyServiceException(10020005, "删除公司信息失败！");
	
	public CompanyServiceException() {
		
	}
	
	public CompanyServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public CompanyServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static CompanyServiceException create(int code, String msg) {
		return new CompanyServiceException(code, msg);
	}
	
/*	public CompanyServiceException print() {
		log.info("==>BizException, code:" + this.code + ", msg:" + this.errMsg);
		return new CompanyServiceException(this.code, this.errMsg);
	}*/
}
