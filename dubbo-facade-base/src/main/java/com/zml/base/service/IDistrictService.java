package com.zml.base.service;

import com.zml.base.entity.District;
import com.zml.base.exceptions.DistrictServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 省市区表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 17:54:41
 */
public interface IDistrictService {
	
	public District getById(Long id) throws DistrictServiceException;
	
	public Page getListPage(Parameter<District> param) throws DistrictServiceException;
	
	public Long save(District district) throws DistrictServiceException;
	
	public void update(District district) throws DistrictServiceException;
	
	public void delete(Long id) throws DistrictServiceException;
	
}
