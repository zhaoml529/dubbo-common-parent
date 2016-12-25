package com.zml.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IUserDao;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

@Service
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addUser(User user) throws UserServiceException {
		return this.userDao.insert(user);
	}
	
	public User getUserByName(String userName) throws UserServiceException {
		return this.userDao.getUserByName(userName);
	}

	public User getUserByStaffId(String staffId) throws UserServiceException {
		return this.userDao.getUserByStaffId(staffId);
	}

	public User getUserById(Long id) throws UserServiceException {
		return this.userDao.getById(id);
	}

}
