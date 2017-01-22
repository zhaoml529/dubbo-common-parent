package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.user.dao.IResourceDao;
import com.zml.user.entity.Resource;
import com.zml.user.exceptions.ResourceServiceException;
import com.zml.user.service.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;
	
	public Long addResource(Resource resource) throws ResourceServiceException {
		return this.resourceDao.insert(resource);
	}

	public Long updateResrouce(Resource resource)
			throws ResourceServiceException {
		return this.resourceDao.update(resource);
	}

	public List<Resource> findAll(Map<String, Object> map)
			throws ResourceServiceException {
		List<Resource> list = this.resourceDao.getList(map);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
