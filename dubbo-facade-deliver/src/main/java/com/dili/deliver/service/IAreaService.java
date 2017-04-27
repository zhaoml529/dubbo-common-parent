package com.dili.deliver.service;

import java.util.List;
import java.util.Map;

import com.dili.deliver.entity.Area;
import com.dili.deliver.exceptions.AreaServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 片区表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 14:48:11
 */
public interface IAreaService {
	
	public Area getById(Long id) throws AreaServiceException;
	
	public Page getListPage(Parameter<Area> param) throws AreaServiceException;
	
	public List<Area> getList(Map<String, Object> paramMap) throws AreaServiceException;
	
	public Long save(Area area) throws AreaServiceException;
	
	public void update(Area area) throws AreaServiceException;
	
	public void delete(Long id) throws AreaServiceException;
	
}
