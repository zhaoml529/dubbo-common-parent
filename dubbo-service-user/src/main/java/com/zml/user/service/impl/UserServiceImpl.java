package com.zml.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.user.dao.IUserDao;
import com.zml.user.entity.User;
import com.zml.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	public User getUserByName(String userName) throws Exception {
		return this.userDao.getUserByName(userName);
	}

	public User getUserByStaffId(String staffId) throws Exception {
		return this.userDao.getUserByStaffId(staffId);
	}

	public User getUserById(Long id) throws Exception {
		return this.userDao.getUserById(id);
	}

}
