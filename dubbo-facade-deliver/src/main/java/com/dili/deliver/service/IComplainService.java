package com.dili.deliver.service;

import com.dili.deliver.entity.Complain;
import com.dili.deliver.exceptions.ComplainServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 投诉表
 * @description: 
 * @author: zhengrs
 * @date: 2017年4月19日 下午1:45:05
 */
public interface IComplainService {
	
	public Complain getById(Long id) throws ComplainServiceException;
	
	public Page getListPage(Parameter<Complain> param) throws ComplainServiceException;
	
	public Long save(Complain Complain) throws ComplainServiceException;
	
	public void update(Complain Complain) throws ComplainServiceException;
	
	public void delete(Long id) throws ComplainServiceException;
	
}
