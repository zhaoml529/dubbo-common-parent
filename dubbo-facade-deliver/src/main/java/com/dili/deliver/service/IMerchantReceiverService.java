package com.dili.deliver.service;

import com.dili.deliver.entity.MerchantReceiver;
import com.dili.deliver.exceptions.MerchantReceiverServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 商户收货人关联表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:31:41
 */
public interface IMerchantReceiverService {
	
	public MerchantReceiver getById(Long id) throws MerchantReceiverServiceException;
	
	public Page getListPage(Parameter<MerchantReceiver> param) throws MerchantReceiverServiceException;
	
	public Long save(MerchantReceiver merchantReceiver) throws MerchantReceiverServiceException;
	
	public void update(MerchantReceiver merchantReceiver) throws MerchantReceiverServiceException;
	
	public void delete(Long id) throws MerchantReceiverServiceException;
	
}
