package com.dili.deliver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dili.deliver.dao.IComPetitorDao;
import com.dili.deliver.entity.ComPetitor;
import com.dili.deliver.exceptions.ComPetitorServiceException;
import com.dili.deliver.service.IComPetitorService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@Service("comPetitorService")
public class ComPetitorServiceImpl implements IComPetitorService{

	@Autowired
	private IComPetitorDao comPetitorDao;

	@Override
	public Long save(ComPetitor comPetitor) throws ComPetitorServiceException {
		return comPetitorDao.insert(comPetitor);
	}

	@Override
	public void update(ComPetitor comPetitor) throws ComPetitorServiceException {
		comPetitorDao.update(comPetitor);
	}

	@Override
	public void delete(Long id) throws ComPetitorServiceException {
		comPetitorDao.deleteById(id);
	}

	@Override
	public ComPetitor findById(Long id) throws ComPetitorServiceException {
		return comPetitorDao.getById(id);
	}

	@Override
	public Page getListPage(Parameter<ComPetitor> param) throws ComPetitorServiceException {
		param.initPage();	
		Page page = this.comPetitorDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	 

}
