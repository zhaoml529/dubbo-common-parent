package com.zml.log.service;

import com.zml.log.entity.UserOperateLog;
import com.zml.log.exceptions.UserOperateServiceException;

public interface IUserOperateLogService {

	public Long addLog(UserOperateLog userOperateLog) throws UserOperateServiceException;
}
