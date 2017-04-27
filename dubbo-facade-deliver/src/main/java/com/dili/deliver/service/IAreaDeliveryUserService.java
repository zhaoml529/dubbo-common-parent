package com.dili.deliver.service;

import java.util.List;

import com.dili.deliver.entity.AreaDeliveryUser;
import com.dili.deliver.exceptions.AreaDeliveryUserServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 片区送货员
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:48:12
 */
public interface IAreaDeliveryUserService {
	
	public AreaDeliveryUser getById(Long id) throws AreaDeliveryUserServiceException;
	
	public Page getListPage(Parameter<AreaDeliveryUser> param) throws AreaDeliveryUserServiceException;
	
	public Long save(AreaDeliveryUser areaDeliveryUser) throws AreaDeliveryUserServiceException;
	
	public Long batchInsert(List<AreaDeliveryUser> list) throws AreaDeliveryUserServiceException;
	
	public void update(AreaDeliveryUser areaDeliveryUser) throws AreaDeliveryUserServiceException;
	
	public void delete(Long id) throws AreaDeliveryUserServiceException;
	
}
