package com.zml.user.service;

import com.zml.user.entity.User;

/**
 * 用户接口
 * @author zhao
 *
 */
public interface IUserService {

	public User getUserByName(String user_name) throws Exception;
	
	public User getUserByStaffId(String staffId) throws Exception;

	public User getUserById(Integer id) throws Exception;
	
}
