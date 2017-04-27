package com.zml.user.dao;

import java.util.List;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.Role;

public interface IRoleDao extends BaseDao<Role> {

	public List<Role> getRoleByUserId(Long userId);
}
