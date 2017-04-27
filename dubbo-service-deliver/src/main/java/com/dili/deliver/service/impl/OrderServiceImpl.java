package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IOrderDao;
import com.dili.deliver.entity.Order;
import com.dili.deliver.exceptions.OrderServiceException;
import com.dili.deliver.service.IOrderService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	
	@Override
	public Order getById(Long id) throws OrderServiceException {
		return orderDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Order> param) throws OrderServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.orderDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Order order) throws OrderServiceException {
		return this.orderDao.insert(order);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Order order) throws OrderServiceException {
		this.orderDao.update(order);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws OrderServiceException {
		this.orderDao.deleteById(id);
	}
	
}
