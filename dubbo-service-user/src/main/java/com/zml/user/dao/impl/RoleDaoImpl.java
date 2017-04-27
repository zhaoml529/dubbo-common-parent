package com.zml.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IRoleDao;
import com.zml.user.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

	@Override
	public List<Role> getRoleByUserId(Long userId) {
		return this.getSessionTemplate().selectList(this.getStatement("findRoleByUserId"), userId);
	}


}
