package com.zml.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IUserOperateLogDao;
import com.zml.user.entity.UserOperateLog;
import com.zml.user.exceptions.UserOperateServiceException;
import com.zml.user.service.IUserOperateLogService;

@Service("userOperateLogService")
public class UserOperateLogServiceImpl implements IUserOperateLogService {

	@Autowired
	private IUserOperateLogDao operateDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addLog(UserOperateLog userOperateLog)
			throws UserOperateServiceException {
		return this.operateDao.insert(userOperateLog);
	}

}
