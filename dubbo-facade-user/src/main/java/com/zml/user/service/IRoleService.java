package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.entity.Role;
import com.zml.user.exceptions.RoleServiceException;

public interface IRoleService {

	public Role getById(Long id) throws RoleServiceException;
	
	public Page getListPage(Parameter<Role> param) throws RoleServiceException;
	
	public List<Role> findAll(Map<String, Object> map) throws RoleServiceException;
	
	public Long save(Role role) throws RoleServiceException;
	
	public void update(Role role) throws RoleServiceException;
	
	public void delete(Long id) throws RoleServiceException;
	
	public List<Role> findRoleByUserId(Long userId) throws RoleServiceException;
}
