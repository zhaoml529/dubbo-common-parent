package com.zml.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.Role;

public interface IRoleDao extends BaseDao<Role> {

	public List<Role> getRoleByUserId(@Param("userId") Long userId);
}
