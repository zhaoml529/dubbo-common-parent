package com.zml.common.web.service;

import com.zml.user.entity.UserOperateLog;
import com.zml.user.exceptions.UserOperateServiceException;

public interface IUserOperateLogService {

	public Long addLog(UserOperateLog userOperateLog) throws UserOperateServiceException;
}
