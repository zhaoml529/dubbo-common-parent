package com.zml.user.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IUserDao;
import com.zml.user.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User getUserByName(String userName) {
		return this.getSessionTemplate().selectOne(this.getStatement("getUserByName"), userName);
	}

	@Override
	public User getUserByStaffNum(String staffNum) {
		return this.getSessionTemplate().selectOne(this.getStatement("getUserByStaffNum"), staffNum);
	}

	@Override
	public void updateUserStatus(Long id, Integer status) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		parameter.put("status", status);
		this.getSessionTemplate().update(this.getStatement("updateUserStatus"), parameter);
		
	}


}
