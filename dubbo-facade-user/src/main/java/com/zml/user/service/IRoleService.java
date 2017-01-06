package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Role;

public interface IRoleService {

	public Long addRole(Role role);
	
	public Long updateRole(Role role);
	
	public List<Role> findAll(Map<String, Object> map);
}
