package com.dili.deliver.dao.impl;

import org.springframework.stereotype.Repository;

import com.dili.deliver.dao.IOrderGoodsDao;
import com.dili.deliver.entity.OrderGoods;
import com.zml.core.dao.impl.BaseDaoImpl;

/**
 * 订单商品表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:24:49
 */
@Repository("orderGoodsDao")
public class OrderGoodsDaoImpl extends BaseDaoImpl<OrderGoods> implements IOrderGoodsDao {
	
}
