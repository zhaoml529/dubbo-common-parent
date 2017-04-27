package com.zml.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.dao.IResourceDao;
import com.zml.user.entity.Resource;
import com.zml.user.exceptions.ResourceServiceException;
import com.zml.user.service.IResourceService;


@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;
	
	@Override
	public Resource getById(Long id) throws ResourceServiceException {
		return resourceDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Resource> param) throws ResourceServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.resourceDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Resource resource) throws ResourceServiceException {
		return this.resourceDao.insert(resource);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Resource resource) throws ResourceServiceException {
		this.resourceDao.update(resource);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws ResourceServiceException {
		this.resourceDao.deleteById(id);
	}
	
}
