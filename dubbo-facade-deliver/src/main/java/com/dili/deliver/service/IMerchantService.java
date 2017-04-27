package com.dili.deliver.service;

import com.dili.deliver.entity.Merchant;
import com.dili.deliver.exceptions.MerchantServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 商户表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:31:40
 */
public interface IMerchantService {
	
	public Merchant getById(Long id) throws MerchantServiceException;
	
	public Page getListPage(Parameter<Merchant> param) throws MerchantServiceException;
	
	public Long save(Merchant merchant) throws MerchantServiceException;
	
	public void update(Merchant merchant) throws MerchantServiceException;
	
	public void delete(Long id) throws MerchantServiceException;
	
}
