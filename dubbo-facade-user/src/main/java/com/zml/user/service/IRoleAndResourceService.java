package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Resource;
import com.zml.user.entity.RoleAndResource;
import com.zml.user.exceptions.RoleAndResourceException;

public interface IRoleAndResourceService {
	
	public List<RoleAndResource> findAll(Map<String, Object> map) throws RoleAndResourceException;
	
	public Long add(RoleAndResource rar) throws RoleAndResourceException;
	
	public Long delete(Long id) throws RoleAndResourceException;
	
	public Long deleteByRole(Long roleId) throws RoleAndResourceException;
	
	public Long deleteByResource(Long resourceId) throws RoleAndResourceException;
	
	public List<Resource> getResourceByRoleId(Long roleId) throws RoleAndResourceException;
}
