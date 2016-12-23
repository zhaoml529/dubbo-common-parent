package com.zml.user.service;

import com.zml.user.entity.User;

/**
 * 用户接口
 * @author zhao
 *
 */
public interface IUserService {

	public User getUserByName(String userName) throws Exception;
	
	public User getUserByStaffId(String staffId) throws Exception;

	public User getUserById(Long id) throws Exception;
	
}
