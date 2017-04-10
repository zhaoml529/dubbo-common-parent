package com.zml.common.constant;

/**
 * 缓存常量,定义缓存KEY的值 
 * @author zhaomingliang
 * @date 2017年2月10日
 */
public class CacheConstant {

	/**
	 * 用户列表
	 */
	public static final String ALL_USER_LIST = "ALL_USER_LIST";
	
	/**
	 * 当前用户(id为key)
	 */
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID_";
	
	/**
	 * 用户权限字符串列表
	 */
	public static final String USER_PERMISSION_KEY = "USER_PERMISSION_KEY_";
	
	/**
	 * 所有角色对应的数据权限key
	 */
	public static final String DATA_PERMISSION_KEY = "DATA_PERMISSION_KEY";
	
	/**
	 * 密码重试次数key
	 */
	public static final String PWD_RETRY_COUNT =  "PWD_RETRY_COUNT_";
	
	/**
	 * 是否显示验证码的缓存key
	 */
	public static final String JCAPTCHA_ENABLED =  "JCAPTCHA_ENABLED_";
}
