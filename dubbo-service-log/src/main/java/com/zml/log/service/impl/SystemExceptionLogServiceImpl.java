package com.zml.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.log.dao.ISystemExceptionLogDao;
import com.zml.log.entity.SystemExceptionLog;
import com.zml.log.exceptions.LogServiceException;
import com.zml.log.service.ISystemExceptionLogService;

@Service("systemExceptionLogService")
public class SystemExceptionLogServiceImpl implements ISystemExceptionLogService {

	@Autowired
	private ISystemExceptionLogDao systemLogDao;
	
	@Override
	public Long addLog(SystemExceptionLog systemExceptionLog)
			throws LogServiceException {
		return this.systemLogDao.insert(systemExceptionLog);
	}

}
