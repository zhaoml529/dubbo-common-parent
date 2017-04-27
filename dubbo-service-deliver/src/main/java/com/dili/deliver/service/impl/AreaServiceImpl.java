package com.dili.deliver.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.deliver.dao.IAreaDao;
import com.dili.deliver.entity.Area;
import com.dili.deliver.entity.AreaDeliveryUser;
import com.dili.deliver.exceptions.AreaServiceException;
import com.dili.deliver.service.IAreaDeliveryUserService;
import com.dili.deliver.service.IAreaService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


@Service("areaService")
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private IAreaDao areaDao;
	
	@Autowired
	private IAreaDeliveryUserService deliverUserService;
	
	@Override
	public Area getById(Long id) throws AreaServiceException {
		Area area = this.areaDao.getById(id);
		if(area != null) {
			return area;
		} else {
			throw AreaServiceException.ADD_AREA_ERR;
		}
	}
	
	@Override
	public Page getListPage(Parameter<Area> param) throws AreaServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.areaDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Area area) throws AreaServiceException {
		String userIds = area.getUserIds();
		Long areaId = this.areaDao.insert(area);
		List<AreaDeliveryUser> deliverList = new ArrayList<AreaDeliveryUser>();
		for(String userId : userIds.split(",")) {
			AreaDeliveryUser deliverUser = new AreaDeliveryUser();
			deliverUser.setAreaId(areaId);
			deliverUser.setUserId(Long.valueOf(userId));
			deliverList.add(deliverUser);
		}
		this.deliverUserService.batchInsert(deliverList);
		return areaId;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Area area) throws AreaServiceException {
		this.areaDao.update(area);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws AreaServiceException {
		this.areaDao.deleteById(id);
	}

	@Override
	public List<Area> getList(Map<String, Object> paramMap)
			throws AreaServiceException {
		List<Area> list = this.areaDao.getList(paramMap);
		if(CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}
	
}
