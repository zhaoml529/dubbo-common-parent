package com.zml.user.dao;

import java.util.List;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.User;

public interface IUserDao extends BaseDao<User> {
	
	public User getUserByName(String userName);
	
	public User getUserByStaffNum(String staffNum);
	
	public void updateUserStatus(Long id, Integer status);

	public List<String> getPermissionByUserId(String userId);
	
	public List<String> getPermissionByStaffNuym(String staffNum);
}
