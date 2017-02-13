package com.zml.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zml.core.dao.BaseDao;
import com.zml.user.entity.Resource;
import com.zml.user.entity.RoleAndResource;

public interface IRoleAndResourceDao extends BaseDao<RoleAndResource> {

	public long deleteByRole(@Param("roleId") Long roleId);
	
	public long deleteByResource(@Param("resourceId") Long resourceId);
	
	public List<Resource> getResourceByRoleId(@Param("roleId") Long roleId);
}
