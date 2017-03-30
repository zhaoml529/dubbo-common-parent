package com.zml.activiti.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.activiti.dao.IProcessModelDao;
import com.zml.activiti.entity.ProcessModel;
import com.zml.activiti.exceptions.ProcessModelServiceException;
import com.zml.activiti.service.IProcessModelService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@Service("processModelService")
public class ProcessModelServiceImpl implements IProcessModelService {

	@Autowired
	private IProcessModelDao processModelDao;
	
	@Override
	public Long add(ProcessModel processModel)
			throws ProcessModelServiceException {
		if(CollectionUtils.isNotEmpty(this.findRepeatByName(processModel.getProcessName(), processModel.getId()))) {
			throw ProcessModelServiceException.ALREAD_EXIST;
		}
		this.processModelDao.insert(processModel);
		return null;
	}

	@Override
	public void update(ProcessModel processModel)
			throws ProcessModelServiceException {
		if(CollectionUtils.isNotEmpty(this.findRepeatByName(processModel.getProcessName(), processModel.getId()))) {
			throw ProcessModelServiceException.ALREAD_EXIST;
		}
		this.processModelDao.update(processModel);
	}

	@Override
	public Page findAllModel(Parameter<ProcessModel> param)
			throws ProcessModelServiceException {
		param.initPage();
		return this.processModelDao.listPage(param.getPageParam(), param.getParamMap());
	}

	@Override
	public List<ProcessModel> findAllModel(Map<String, Object> params)
			throws ProcessModelServiceException {
		return this.processModelDao.getList(params);
	}

	@Override
	public ProcessModel findById(Long modelId)
			throws ProcessModelServiceException {
		return this.processModelDao.getById(modelId);
	}

	@Override
	public ProcessModel findByKey(String processKey)
			throws ProcessModelServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processKey", processKey);
		return this.processModelDao.getBy(paramMap);
	}

	@Override
	public void deployModel(Long modelId) throws ProcessModelServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProcessModel> findRepeatByName(String modelName, Long modelId)
			throws ProcessModelServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processName", modelName);
		paramMap.put("id", modelId);
		List<ProcessModel> pmList = this.processModelDao.getList(paramMap);
		if(CollectionUtils.isEmpty(pmList)) {
			return Collections.emptyList();
		} else {
			return pmList;
		}
	}

	@Override
	public void deleteModel(Long modelId) throws ProcessModelServiceException {
		this.processModelDao.deleteById(modelId);
	}

}
