package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.RoleAndResource;

public interface IRoleAndResourceService {
	
	public List<RoleAndResource> findAll(Map<String, Object> map) throws Exception;
	
	public Long add(RoleAndResource rar) throws Exception;
	
	public Long delete(Long id) throws Exception;
	
	public Long deleteByRole(Long roleId) throws Exception;
	
	public Long deleteByResource(Long resourceId) throws Exception;
}
