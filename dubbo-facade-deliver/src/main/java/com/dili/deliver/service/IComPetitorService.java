package com.dili.deliver.service;

import com.dili.deliver.entity.ComPetitor;
import com.dili.deliver.exceptions.ComPetitorServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 竞争对手接口
 * @author fuping
 *
 */
public interface IComPetitorService {

	/**
	 * add ComPetitor
	 * @param comPetitor
	 * @return
	 * @throws ComPetitorServiceException
	 */
	public Long save(ComPetitor comPetitor)throws ComPetitorServiceException;
	
	/** update ComPetitor
	 * @param comPetitor
	 * @throws ComPetitorServiceException
	 */
	public void update(ComPetitor comPetitor)throws ComPetitorServiceException;
	
	/**
	 * @param id
	 * @throws ComPetitorServiceException
	 */
	public void delete(Long id) throws ComPetitorServiceException;
	/**
	 * @param id
	 * @return
	 * @throws ComPetitorServiceException
	 */
	public ComPetitor findById(Long id)throws ComPetitorServiceException;
	
	/**
	 * getListPage
	 * @param param
	 * @return
	 * @throws ComPetitorServiceException
	 */
	public Page getListPage(Parameter<ComPetitor> param)throws ComPetitorServiceException;
}
