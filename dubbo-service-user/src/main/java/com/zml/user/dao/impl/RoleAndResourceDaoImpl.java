package com.zml.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IRoleAndResourceDao;
import com.zml.user.entity.Resource;
import com.zml.user.entity.RoleAndResource;

@Repository("rarDao")
public class RoleAndResourceDaoImpl extends BaseDaoImpl<RoleAndResource> implements IRoleAndResourceDao {

	@Override
	public long deleteByRole(Long roleId) {
		return this.getSessionTemplate().delete(this.getStatement("deleteByRole"), roleId);
	}

	@Override
	public long deleteByResource(Long resourceId) {
		return this.getSessionTemplate().delete(this.getStatement("deleteByResource"), resourceId);
	}

	@Override
	public List<Resource> getResourceByRoleId(Long roleId) {
		return this.getSessionTemplate().selectList(this.getStatement("getResourceByRoleId"), roleId);
	}

}
