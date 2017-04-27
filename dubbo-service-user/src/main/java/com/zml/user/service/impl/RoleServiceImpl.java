package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.dao.IRoleDao;
import com.zml.user.entity.Role;
import com.zml.user.exceptions.RoleServiceException;
import com.zml.user.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public Role getById(Long id) throws RoleServiceException {
		return roleDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Role> param) throws RoleServiceException {
		param.initPage();	
		Page page = this.roleDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Role role) throws RoleServiceException {
		return this.roleDao.insert(role);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Role role) throws RoleServiceException {
		this.roleDao.update(role);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws RoleServiceException {
		this.roleDao.deleteById(id);
	}
	
	@Override
	public List<Role> findAll(Map<String, Object> map)
			throws RoleServiceException {
		List<Role> list = this.roleDao.getList(map);
		if(CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

	@Override
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
