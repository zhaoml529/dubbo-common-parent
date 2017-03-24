package com.zml.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.log.dao.IUserOperateLogDao;
import com.zml.log.entity.UserOperateLog;

@Repository("operateLogDao")
public class UserOperateLogDaoImpl extends BaseDaoImpl<UserOperateLog>
		implements IUserOperateLogDao {

	
}
