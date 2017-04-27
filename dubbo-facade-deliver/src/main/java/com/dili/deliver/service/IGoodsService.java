package com.dili.deliver.service;

import com.dili.deliver.entity.Goods;
import com.dili.deliver.exceptions.GoodsServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 商品表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:09:31
 */
public interface IGoodsService {
	
	public Goods getById(Long id) throws GoodsServiceException;
	
	public Page getListPage(Parameter<Goods> param) throws GoodsServiceException;
	
	public Long save(Goods goods) throws GoodsServiceException;
	
	public void update(Goods goods) throws GoodsServiceException;
	
	public void delete(Long id) throws GoodsServiceException;
	
}
