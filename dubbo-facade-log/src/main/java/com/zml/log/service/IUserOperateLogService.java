package com.zml.log.service;

import com.zml.log.entity.UserOperateLog;
import com.zml.log.exceptions.LogServiceException;

public interface IUserOperateLogService {

	public Long addLog(UserOperateLog userOperateLog) throws LogServiceException;
}
