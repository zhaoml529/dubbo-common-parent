package com.zml.common.web.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.web.enums.OperateLogStatusEnum;
import com.zml.common.web.enums.OperateLogTypeEnum;
import com.zml.common.web.utils.SessionUtil;
import com.zml.user.entity.User;
import com.zml.user.entity.UserOperateLog;
import com.zml.user.service.IUserOperateLogService;

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
		User user = SessionUtil.getUserFromSession();
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
		}
	}
}
