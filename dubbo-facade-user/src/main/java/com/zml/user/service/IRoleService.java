package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Role;
import com.zml.user.exceptions.RoleServiceException;

public interface IRoleService {

	public Long addRole(Role role) throws RoleServiceException;
	
	public Long updateRole(Role role) throws RoleServiceException;
	
	public List<Role> findAll(Map<String, Object> map) throws RoleServiceException;
	
	public List<Role> findRoleByUserId(Long userId) throws RoleServiceException;
}
