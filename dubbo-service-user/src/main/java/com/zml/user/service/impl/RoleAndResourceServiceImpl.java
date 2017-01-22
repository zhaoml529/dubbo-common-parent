package com.zml.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.user.dao.IRoleAndResourceDao;
import com.zml.user.entity.RoleAndResource;
import com.zml.user.service.IRoleAndResourceService;

@Service("roleAndResourceService")
public class RoleAndResourceServiceImpl implements IRoleAndResourceService {

	@Autowired
	private IRoleAndResourceDao rarDao;
	
	public List<RoleAndResource> findAll(Map<String, Object> map)
			throws Exception {
		return this.rarDao.getList(map);
	}

	public Long add(RoleAndResource rar) throws Exception {
		return this.rarDao.insert(rar);
	}

	public Long delete(Long id) throws Exception {
		return this.rarDao.deleteById(id);
	}

	public Long deleteByRole(Long roleId) throws Exception {
		 return this.rarDao.deleteByRole(roleId);
	}

	public Long deleteByResource(Long resourceId) throws Exception {
		return this.rarDao.deleteByResource(resourceId);
	}

}
