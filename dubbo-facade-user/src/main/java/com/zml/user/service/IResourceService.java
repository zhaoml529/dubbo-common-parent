package com.zml.user.service;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.entity.Resource;
import com.zml.user.exceptions.ResourceServiceException;

/**
 * 
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:15:55
 */
public interface IResourceService {
	
	public Resource getById(Long id) throws ResourceServiceException;
	
	public Page getListPage(Parameter<Resource> param) throws ResourceServiceException;
	
	public Long save(Resource resource) throws ResourceServiceException;
	
	public void update(Resource resource) throws ResourceServiceException;
	
	public void delete(Long id) throws ResourceServiceException;
	
}
