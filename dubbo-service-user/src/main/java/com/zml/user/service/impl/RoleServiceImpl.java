package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IRoleDao;
import com.zml.user.entity.Role;
import com.zml.user.exceptions.RoleServiceException;
import com.zml.user.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addRole(Role role) throws RoleServiceException {
		return this.roleDao.insert(role);
	}

	public Long updateRole(Role role) throws RoleServiceException {
		return this.roleDao.update(role);
	}

	public List<Role> findAll(Map<String, Object> map)
			throws RoleServiceException {
		List<Role> list = this.roleDao.getList(map);
		if(CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

	public List<Role> findRoleByUserId(Long userId)
			throws RoleServiceException {
		List<Role> roleList = this.roleDao.getRoleByUserId(userId);
		if(CollectionUtils.isEmpty(roleList)) {
			return Collections.emptyList();
		} else {
			return roleList;
		}
	}

}
