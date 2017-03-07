package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IUserOperateLogDao;
import com.zml.user.entity.UserOperateLog;

@Repository("operateDao")
public class UserOperateLogDaoImpl extends BaseDaoImpl<UserOperateLog>
		implements IUserOperateLogDao {


}
