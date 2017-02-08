package com.zml.user.service;

import java.util.List;

import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;

/**
 * 用户接口
 * @author zhao
 *
 */
public interface IUserService {

	public Long addUser(User user) throws UserServiceException;
	
	public List<User> getAllUser() throws UserServiceException;
	
	public User getUserByName(String userName) throws UserServiceException;
	
	public User getUserByStaffId(String staffId) throws UserServiceException;
	
	public User getUserById(Long id) throws UserServiceException;
	
	/**
	 * 通过用户名查询用户是否存在
	 * @param user
	 * @return
	 * @throws UserServiceException
	 */
	public boolean isUserExist(User user) throws UserServiceException;
	
	public void updateUser(User user) throws UserServiceException;
	
	public void deleteUser(Long id) throws UserServiceException;

}
