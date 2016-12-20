package com.zml.user.dao;

import com.zml.user.entity.User;

public interface IUserDao {
	public User getUserByName(String userName) throws Exception;
	
	public User getUserByStaffId(String staffId) throws Exception;

	public User getUserById(Long userId) throws Exception;
}
