package com.zml.common.exceptions;

/**
 * 业务异常基础类(所有业务异常都必须继承于此异常)
 * 定义异常时，需要先确定异常所属模块。例如：添加用户报错 可以定义为 [10010001] 前四位数为系统模块编号，后4位为错误代码(唯一)。
 * 
 * 用户服务异常 1001
 * 公司服务接口 1002
 * 部门服务接口 1003
 * 岗位服务接口 1004
 * 编制服务接口 1005
 * 角色服务接口 1006
 * 资源服务接口 1007
 * 
 * 流程服务接口 1008 
 * 
 * 用户操作日志异常8008
 * 系统异常 9009
 * 
 * @author zhaomingliang
 * @date 2016年11月18日
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 172577273171844834L;

	/**
	 * 数据库操作,insert返回0
	 */
	public static final ServiceException DB_INSERT_RESULT_0 = new ServiceException(90090001, "数据库操作,insert返回0");

	/**
	 * 数据库操作,update返回0
	 */
	public static final ServiceException DB_UPDATE_RESULT_0 = new ServiceException(90090002, "数据库操作,update返回0");

	/**
	 * 数据库操作,selectOne返回null
	 */
	public static final ServiceException DB_SELECTONE_IS_NULL = new ServiceException(90090003, "数据库操作,selectOne返回null");

	/**
	 * 数据库操作,list返回null
	 */
	public static final ServiceException DB_LIST_IS_NULL = new ServiceException(90090004, "数据库操作,list返回null");

	/**
	 * Token 验证不通过
	 */
	public static final ServiceException TOKEN_IS_ILLICIT = new ServiceException(90090005, "Token 验证非法");
	/**
	 * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
	 */
	public static final ServiceException SESSION_IS_OUT_TIME = new ServiceException(90090006, "会话超时");

	/**
	 * 获取序列出错
	 */
	public static final ServiceException DB_GET_SEQ_NEXT_VALUE_ERROR = new ServiceException(90090007, "获取序列出错");

	
	protected String errMsg;
	
	protected Integer code;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(Integer code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.errMsg = String.format(msgFormat, args);
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
