package com.zml.user.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IUserDao;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addUser(User user) throws UserServiceException {
		return this.userDao.insert(user);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void updateUser(User user) throws UserServiceException {
		this.userDao.update(user);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void deleteUser(Long id) throws UserServiceException {
		this.userDao.deleteById(id);
		
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

	public boolean isUserExist(User user) throws UserServiceException {
		User u = this.getUserByName(user.getUserName());
		if(u != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<User> getAllUser() throws UserServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<User> list = this.userDao.getList(paramMap);
		if(CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

}
