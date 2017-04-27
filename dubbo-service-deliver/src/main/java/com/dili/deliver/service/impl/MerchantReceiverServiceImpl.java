package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IMerchantReceiverDao;
import com.dili.deliver.entity.MerchantReceiver;
import com.dili.deliver.exceptions.MerchantReceiverServiceException;
import com.dili.deliver.service.IMerchantReceiverService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("merchantReceiverService")
public class MerchantReceiverServiceImpl implements IMerchantReceiverService {

	@Autowired
	private IMerchantReceiverDao merchantReceiverDao;
	
	@Override
	public MerchantReceiver getById(Long id) throws MerchantReceiverServiceException {
		return merchantReceiverDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<MerchantReceiver> param) throws MerchantReceiverServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.merchantReceiverDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(MerchantReceiver merchantReceiver) throws MerchantReceiverServiceException {
		return this.merchantReceiverDao.insert(merchantReceiver);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(MerchantReceiver merchantReceiver) throws MerchantReceiverServiceException {
		this.merchantReceiverDao.update(merchantReceiver);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws MerchantReceiverServiceException {
		this.merchantReceiverDao.deleteById(id);
	}
	
}
