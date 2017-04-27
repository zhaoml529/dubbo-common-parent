package com.dili.deliver.dao.impl;

import org.springframework.stereotype.Repository;

import com.dili.deliver.dao.IOrderDao;
import com.dili.deliver.entity.Order;
import com.zml.core.dao.impl.BaseDaoImpl;

/**
 * 订单表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:24:48
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements IOrderDao {
	
}
