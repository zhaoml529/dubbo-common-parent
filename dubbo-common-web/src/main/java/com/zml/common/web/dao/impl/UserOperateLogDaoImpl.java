package com.zml.common.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.common.web.dao.IUserOperateLogDao;
import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.entity.UserOperateLog;

@Repository("operateDao")
public class UserOperateLogDaoImpl extends BaseDaoImpl<UserOperateLog>
		implements IUserOperateLogDao {

	
}
