package com.zml.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.log.dao.ISystemExceptionLogDao;
import com.zml.log.entity.SystemExceptionLog;

@Repository("systemLogDao")
public class SystemExceptionLogDaoImpl extends BaseDaoImpl<SystemExceptionLog>
		implements ISystemExceptionLogDao {


}
