package com.dili.deliver.service;

import com.dili.deliver.entity.OrderGoods;
import com.dili.deliver.exceptions.OrderGoodsServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 订单商品表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:09:30
 */
public interface IOrderGoodsService {
	
	public OrderGoods getById(Long id) throws OrderGoodsServiceException;
	
	public Page getListPage(Parameter<OrderGoods> param) throws OrderGoodsServiceException;
	
	public Long save(OrderGoods orderGoods) throws OrderGoodsServiceException;
	
	public void update(OrderGoods orderGoods) throws OrderGoodsServiceException;
	
	public void delete(Long id) throws OrderGoodsServiceException;
	
}
