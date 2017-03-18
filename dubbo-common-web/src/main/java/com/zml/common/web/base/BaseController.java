package com.zml.common.web.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import com.zml.common.web.entity.FieldErrorMessage;
import com.zml.common.web.enums.OperateLogStatusEnum;
import com.zml.common.web.enums.OperateLogTypeEnum;
import com.zml.user.service.IUserOperateLogService;
// 日志可以用AOP实现，有时间改掉。
@Transactional
public class BaseController {

	@Autowired
	private IUserOperateLogService operateLogService;
	
	/**
	 * 记录登陆日志
	 */
	protected void logLogin(String operateContent) {
		this.setOperateLog(OperateLogTypeEnum.LOGIN, OperateLogStatusEnum.SUCCESS, operateContent);
	}
	
	/**
	 * 记录登陆错误日志
	 * @param operateContent
	 */
	protected void logLoginErr(String operateContent, int...errorCode) {
		this.setOperateLog(OperateLogTypeEnum.LOGOUT, OperateLogStatusEnum.ERROR, operateContent);
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
	protected void logSaveErr(String operateContent, int...errorCode) {
		this.setOperateLog(OperateLogTypeEnum.ADD, OperateLogStatusEnum.ERROR, operateContent, errorCode);
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
	protected void logUpdateErr(String operateContent, int...errorCode) {
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
	protected void logDeleteErr(String operateContent, int...errorCode) {
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
	protected void logQueryErr(String operateContent, int...errorCode) {
		this.setOperateLog(OperateLogTypeEnum.QUERYA, OperateLogStatusEnum.ERROR, operateContent);
	}
	
	
	/**
	 * 组装日志实体
	 * @param logTypeEnum
	 * @param logStatusEnum
	 * @param content
	 */
	private void setOperateLog(OperateLogTypeEnum logTypeEnum, OperateLogStatusEnum logStatusEnum, String content, int... errorCode) {
		/*User user = SessionUtil.getUserFromSession();
		if(user != null) {
			UserOperateLog operateLog= new UserOperateLog();
			operateLog.setUserId(user.getId());
			operateLog.setUserName(user.getUserName());
			operateLog.setStaffNum(user.getStaffNum());
			operateLog.setOperateStatus(logStatusEnum.getValue());
			operateLog.setOperType(logTypeEnum.getValue());
			operateLog.setIp(SessionUtil.getIpAddr());
			operateLog.setContent(content);
			if(errorCode.length > 0) {
				operateLog.setErrorCode(errorCode[0]);
			}
			this.operateLogService.addLog(operateLog);
		}*/
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
}
