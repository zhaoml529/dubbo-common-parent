package com.zml.user.service;

import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;

/**
 * 用户接口
 * @author zhao
 *
 */
public interface IUserService {

	public Long addUser(User user) throws UserServiceException;
	
	public User getUserByName(String userName) throws UserServiceException;
	
	public User getUserByStaffId(String staffId) throws UserServiceException;

	public User getUserById(Long id) throws UserServiceException;
	
}
