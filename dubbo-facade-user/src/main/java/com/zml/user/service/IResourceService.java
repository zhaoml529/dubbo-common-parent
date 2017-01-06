package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Resource;

public interface IResourceService {

	public Long addResource(Resource resource);
	
	public Long updateResrouce(Resource resource);
	
	public List<Resource> findAll(Map<String, Object> map);
}
