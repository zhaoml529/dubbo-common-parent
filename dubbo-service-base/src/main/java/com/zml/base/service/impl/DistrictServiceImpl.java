package com.zml.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.base.dao.IDistrictDao;
import com.zml.base.entity.District;
import com.zml.base.exceptions.DistrictServiceException;
import com.zml.base.service.IDistrictService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("districtService")
public class DistrictServiceImpl implements IDistrictService {

	@Autowired
	private IDistrictDao districtDao;
	
	@Override
	public District getById(Long id) throws DistrictServiceException {
		return districtDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<District> param) throws DistrictServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.districtDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(District district) throws DistrictServiceException {
		return this.districtDao.insert(district);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(District district) throws DistrictServiceException {
		this.districtDao.update(district);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws DistrictServiceException {
		this.districtDao.deleteById(id);
	}
	
}
