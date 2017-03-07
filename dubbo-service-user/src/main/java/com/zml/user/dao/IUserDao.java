package com.zml.user.dao;

import org.apache.ibatis.annotations.Param;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.User;

public interface IUserDao extends BaseDao<User> {
	
	public User getUserByName(String userName);
	
	public User getUserByStaffNum(String staffNum);
	
	public void updateUserStatus(@Param("id") Long id, @Param("status") Integer status);

}
