package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IOrderGoodsDao;
import com.dili.deliver.entity.OrderGoods;
import com.dili.deliver.exceptions.OrderGoodsServiceException;
import com.dili.deliver.service.IOrderGoodsService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements IOrderGoodsService {

	@Autowired
	private IOrderGoodsDao orderGoodsDao;
	
	@Override
	public OrderGoods getById(Long id) throws OrderGoodsServiceException {
		return orderGoodsDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<OrderGoods> param) throws OrderGoodsServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.orderGoodsDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(OrderGoods orderGoods) throws OrderGoodsServiceException {
		return this.orderGoodsDao.insert(orderGoods);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(OrderGoods orderGoods) throws OrderGoodsServiceException {
		this.orderGoodsDao.update(orderGoods);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws OrderGoodsServiceException {
		this.orderGoodsDao.deleteById(id);
	}
	
}
