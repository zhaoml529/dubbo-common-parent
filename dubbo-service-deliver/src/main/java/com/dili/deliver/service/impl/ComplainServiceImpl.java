package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IComplainDao;
import com.dili.deliver.entity.Complain;
import com.dili.deliver.exceptions.ComplainServiceException;
import com.dili.deliver.service.IComplainService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("complainService")
public class ComplainServiceImpl implements IComplainService {

	@Autowired
	private IComplainDao complainDao;
	
	@Override
	public Complain getById(Long id) throws ComplainServiceException {
		return complainDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Complain> param) throws ComplainServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.complainDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Complain complain) throws ComplainServiceException {
		return this.complainDao.insert(complain);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Complain complain) throws ComplainServiceException {
		this.complainDao.update(complain);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws ComplainServiceException {
		this.complainDao.deleteById(id);
	}

}
