package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IGoodsDao;
import com.dili.deliver.entity.Goods;
import com.dili.deliver.exceptions.GoodsServiceException;
import com.dili.deliver.service.IGoodsService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {

	@Autowired
	private IGoodsDao goodsDao;
	
	@Override
	public Goods getById(Long id) throws GoodsServiceException {
		return goodsDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Goods> param) throws GoodsServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.goodsDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Goods goods) throws GoodsServiceException {
		return this.goodsDao.insert(goods);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Goods goods) throws GoodsServiceException {
		this.goodsDao.update(goods);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws GoodsServiceException {
		this.goodsDao.deleteById(id);
	}

}
