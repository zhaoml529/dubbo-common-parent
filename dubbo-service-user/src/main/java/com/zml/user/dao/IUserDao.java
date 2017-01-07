package com.zml.user.dao;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.User;

public interface IUserDao extends BaseDao<User> {
	
	public User getUserByName(String userName);
	
	public User getUserByStaffId(String staffId);

}
