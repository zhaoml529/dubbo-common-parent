package com.zml.log.service;

import com.zml.log.entity.SystemExceptionLog;
import com.zml.log.exceptions.LogServiceException;

public interface ISystemExceptionLogService {

	public Long addLog(SystemExceptionLog systemExceptionLog) throws LogServiceException;
}
