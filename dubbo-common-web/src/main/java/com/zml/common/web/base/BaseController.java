package com.zml.common.web.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.entity.FieldErrorMessage;
import com.zml.common.web.enums.OperateLogStatusEnum;
import com.zml.log.entity.UserOperateLog;
import com.zml.log.service.IUserOperateLogService;
import com.zml.user.entity.User;
/**
 * 记录日志，或者使用@ControllerLog实现
 * @author zhao
 *
 */
public class BaseController {

	@Autowired
	private IUserOperateLogService operateLogService;
	
	@Autowired
	private RedisUtil<User> userRedis;
	
	/**
	 * 记录登陆日志
	 */
	protected void logLogin(String operateContent, User user) {
		this.setLoginLog(OperateLogTypeEnum.LOGIN, OperateLogStatusEnum.SUCCESS, operateContent, user);
	}
	
	/**
	 * 记录登陆错误日志
	 * @param operateContent
	 */
	protected void logLoginErr(String operateContent, User user) {
		this.setLoginLog(OperateLogTypeEnum.LOGOUT, OperateLogStatusEnum.ERROR, operateContent, user);
	}
	
	/**
	 * 记录保存数据日志
	 * @param operateContent
	 */
	protected void logSave(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.ADD, OperateLogStatusEnum.SUCCESS, operateContent);
	}
	
	/**
	 * 记录保存数据错误日志
	 * @param operateContent
	 */
	protected void logSaveErr(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.ADD, OperateLogStatusEnum.ERROR, operateContent);
	}
	
	/**
	 * 记录更新数据日志
	 * @param operateContent
	 */
	protected void logUpdate(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.UPDATE, OperateLogStatusEnum.SUCCESS, operateContent);
	}
	
	/**
	 * 记录更新数据错误日志
	 * @param operateContent
	 */
	protected void logUpdateErr(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.UPDATE, OperateLogStatusEnum.ERROR, operateContent);
	}
	
	/**
	 * 记录删除数据日志
	 * @param operateContent
	 */
	protected void logDelete(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.DELETE, OperateLogStatusEnum.SUCCESS, operateContent);
	}
	
	/**
	 * 记录删除数据错误日志
	 * @param operateContent
	 */
	protected void logDeleteErr(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.DELETE, OperateLogStatusEnum.ERROR, operateContent);
	}
	
	/**
	 * 记录查询数据日志
	 * @param operateContent
	 */
	protected void logQuery(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.QUERYA, OperateLogStatusEnum.SUCCESS, operateContent);
	}
	
	/**
	 * 记录查询数据错误日志
	 * @param operateContent
	 */
	protected void logQueryErr(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.QUERYA, OperateLogStatusEnum.ERROR, operateContent);
	}
	
	
	/**
	 * 组装日志实体
	 * @param logTypeEnum
	 * @param logStatusEnum
	 * @param content
	 */
	private void setOperateLog(OperateLogTypeEnum logTypeEnum, OperateLogStatusEnum logStatusEnum, String content) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userId = (String) request.getAttribute(SystemConstant.CURRENT_USER_ID);
		User user = this.userRedis.getCacheObject(CacheConstant.CURRENT_USER_ID + userId);
		if(user != null) {
			UserOperateLog operateLog= new UserOperateLog();
			operateLog.setUserId(user.getId());
			operateLog.setUserName(user.getUserName());
			operateLog.setStaffNum(user.getStaffNum());
			operateLog.setOperType(logTypeEnum.getValue());
			operateLog.setIp(request.getRemoteAddr());
			operateLog.setContent(content + "-" + logStatusEnum.getValue());
			this.operateLogService.addLog(operateLog);
		}
	}
	
	/**
	 * 组装登陆日志实体
	 * @param logTypeEnum
	 * @param logStatusEnum
	 * @param content
	 * @param user
	 */
	private void setLoginLog(OperateLogTypeEnum logTypeEnum, OperateLogStatusEnum logStatusEnum, String content, User user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserOperateLog operateLog= new UserOperateLog();
		operateLog.setUserId(user.getId());
		operateLog.setUserName(user.getUserName());
		operateLog.setStaffNum(user.getStaffNum());
		operateLog.setOperType(logTypeEnum.getValue());
		operateLog.setIp(request.getRemoteAddr());
		operateLog.setMethodName("com.zml.web.controller.user.LoginController.Login()");
		operateLog.setContent(content + "-" + logStatusEnum.getValue());
		this.operateLogService.addLog(operateLog);
	}
	
	/**
	 * 装载字段验证错误信息
	 * @param list
	 * @return
	 */
	protected List<FieldErrorMessage> loadFieldError(List<FieldError> list) {
		List<FieldErrorMessage> errorList = new ArrayList<FieldErrorMessage>();
		for(FieldError fieldError : list) {
			FieldErrorMessage fieldErrorMessage = new FieldErrorMessage(); 
			fieldErrorMessage.setEntryName(fieldError.getObjectName());
			fieldErrorMessage.setFieldName(fieldError.getField());
			fieldErrorMessage.setErrorMessage(fieldError.getDefaultMessage());
			errorList.add(fieldErrorMessage);
		}
		return errorList;
	}
	
	protected void setDataPermission(Map<String, Object> params) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userId = (String) request.getAttribute(SystemConstant.CURRENT_USER_ID);
		User user = this.userRedis.getCacheObject(CacheConstant.CURRENT_USER_ID + userId);
		// 设置支持数据权限的参数
		params.put(SystemConstant.DATA_PERMISSION, true);							// 开启数据权限查询
		params.put(SystemConstant.DATA_PERMISSION_USER_ID, user.getId());			// 当前用户的数据权限
		params.put(SystemConstant.DATA_PERMISSION_TYPE, user.getDataPermission());	// 当前用户数据权限类型
	}
}
