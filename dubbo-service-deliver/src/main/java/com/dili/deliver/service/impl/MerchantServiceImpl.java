package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IMerchantDao;
import com.dili.deliver.entity.Merchant;
import com.dili.deliver.exceptions.MerchantServiceException;
import com.dili.deliver.service.IMerchantService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("merchantService")
public class MerchantServiceImpl implements IMerchantService {

	@Autowired
	private IMerchantDao merchantDao;
	
	@Override
	public Merchant getById(Long id) throws MerchantServiceException {
		return merchantDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Merchant> param) throws MerchantServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.merchantDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Merchant merchant) throws MerchantServiceException {
		return this.merchantDao.insert(merchant);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Merchant merchant) throws MerchantServiceException {
		this.merchantDao.update(merchant);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws MerchantServiceException {
		this.merchantDao.deleteById(id);
	}
	
}
