package com.zml.user.dao;

import com.zml.user.entity.User;

public interface IUserDao {
	public User getUserByName(String user_name) throws Exception;
	
	public User getUserByStaffId(String staffId) throws Exception;

	public User getUserById(Integer id) throws Exception;
}
