package com.dili.deliver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IAreaDeliveryUserDao;
import com.dili.deliver.entity.AreaDeliveryUser;
import com.dili.deliver.exceptions.AreaDeliveryUserServiceException;
import com.dili.deliver.service.IAreaDeliveryUserService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("areaDeliveryUserService")
public class AreaDeliveryUserServiceImpl implements IAreaDeliveryUserService {

	@Autowired
	private IAreaDeliveryUserDao areaDeliveryUserDao;
	
	@Override
	public AreaDeliveryUser getById(Long id) throws AreaDeliveryUserServiceException {
		return areaDeliveryUserDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<AreaDeliveryUser> param) throws AreaDeliveryUserServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.areaDeliveryUserDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(AreaDeliveryUser areaDeliveryUser) throws AreaDeliveryUserServiceException {
		return this.areaDeliveryUserDao.insert(areaDeliveryUser);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(AreaDeliveryUser areaDeliveryUser) throws AreaDeliveryUserServiceException {
		this.areaDeliveryUserDao.update(areaDeliveryUser);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws AreaDeliveryUserServiceException {
		this.areaDeliveryUserDao.deleteById(id);
	}

	@Override
	public Long batchInsert(List<AreaDeliveryUser> list)
			throws AreaDeliveryUserServiceException {
		return this.areaDeliveryUserDao.insert(list);
	}
	
}
