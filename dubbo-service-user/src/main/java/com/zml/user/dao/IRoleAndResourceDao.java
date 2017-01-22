package com.zml.user.dao;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.RoleAndResource;

public interface IRoleAndResourceDao extends BaseDao<RoleAndResource> {

	public long deleteByRole(Long roleId);
	
	public long deleteByResource(Long resourceId);
}
