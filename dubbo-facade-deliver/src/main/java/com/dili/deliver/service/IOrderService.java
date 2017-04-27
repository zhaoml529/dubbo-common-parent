package com.dili.deliver.service;

import com.dili.deliver.entity.Order;
import com.dili.deliver.exceptions.OrderServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 订单表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:09:29
 */
public interface IOrderService {
	
	public Order getById(Long id) throws OrderServiceException;
	
	public Page getListPage(Parameter<Order> param) throws OrderServiceException;
	
	public Long save(Order order) throws OrderServiceException;
	
	public void update(Order order) throws OrderServiceException;
	
	public void delete(Long id) throws OrderServiceException;
	
}
