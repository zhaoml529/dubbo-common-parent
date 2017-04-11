package com.zml.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zml.log.dao.ISystemExceptionLogDao;
import com.zml.log.entity.SystemExceptionLog;
import com.zml.log.exceptions.LogServiceException;
import com.zml.log.service.ISystemExceptionLogService;

@Service("systemExceptionLogService")
public class SystemExceptionLogServiceImpl implements ISystemExceptionLogService {

	@Autowired
	@Qualifier("systemLogDao")
	private ISystemExceptionLogDao systemLogDao;
	
	@Override
	public Long addLog(SystemExceptionLog systemExceptionLog)
			throws LogServiceException {
		return this.systemLogDao.insert(systemExceptionLog);
	}

	@Override
	public List<SystemExceptionLog> getList() throws LogServiceException {
		return this.systemLogDao.getList(null);
	}

}
