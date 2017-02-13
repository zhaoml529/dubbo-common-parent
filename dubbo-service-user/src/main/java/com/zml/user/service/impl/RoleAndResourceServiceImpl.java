package com.zml.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IRoleAndResourceDao;
import com.zml.user.entity.Resource;
import com.zml.user.entity.RoleAndResource;
import com.zml.user.exceptions.RoleAndResourceException;
import com.zml.user.service.IRoleAndResourceService;

@Service("roleAndResourceService")
public class RoleAndResourceServiceImpl implements IRoleAndResourceService {

	@Autowired
	private IRoleAndResourceDao rarDao;
	
	public List<RoleAndResource> findAll(Map<String, Object> map)
			throws RoleAndResourceException {
		return this.rarDao.getList(map);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long add(RoleAndResource rar) throws RoleAndResourceException {
		return this.rarDao.insert(rar);
	}

	public Long delete(Long id) throws RoleAndResourceException {
		return this.rarDao.deleteById(id);
	}

	public Long deleteByRole(Long roleId) throws RoleAndResourceException {
		 return this.rarDao.deleteByRole(roleId);
	}

	public Long deleteByResource(Long resourceId) throws RoleAndResourceException {
		return this.rarDao.deleteByResource(resourceId);
	}

	public List<Resource> getResourceByRoleId(Long roleId) {
		return this.rarDao.getResourceByRoleId(roleId);
	}

}
