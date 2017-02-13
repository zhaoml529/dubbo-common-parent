package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Resource;
import com.zml.user.exceptions.ResourceServiceException;

public interface IResourceService {

	public Long addResource(Resource resource) throws ResourceServiceException;
	
	public Long updateResrouce(Resource resource) throws ResourceServiceException;
	
	public List<Resource> findAll(Map<String, Object> map) throws ResourceServiceException;
	
}
