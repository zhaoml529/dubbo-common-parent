package com.zml.activiti.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.activiti.dao.IProcessInstanceDao;
import com.zml.activiti.entity.ProcessInstance;
import com.zml.activiti.exceptions.ProcessDefineServiceException;
import com.zml.activiti.service.IProcessInstanceService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@Service("processInstanceService")
public class ProcessInstanceServiceImpl implements IProcessInstanceService {

	@Autowired
	private IProcessInstanceDao processInstanceDao;
	
	@Override
	public void add(ProcessInstance processInstance)
			throws ProcessDefineServiceException {
		this.processInstanceDao.insert(processInstance);

	}

	@Override
	public void update(ProcessInstance processInstance)
			throws ProcessDefineServiceException {
		this.processInstanceDao.update(processInstance);

	}

	@Override
	public Page findAllInstance(Parameter<ProcessInstance> param)
			throws ProcessDefineServiceException {
		param.initPage();
		return this.processInstanceDao.listPage(param.getPageParam(), param.getParamMap());
	}

	@Override
	public List<ProcessInstance> findAllInstance(Map<String, Object> params)
			throws ProcessDefineServiceException {
		return this.processInstanceDao.getList(params);
	}

	@Override
	public ProcessInstance findById(Long id)
			throws ProcessDefineServiceException {
		return this.processInstanceDao.getById(id);
	}

	@Override
	public void deleteByDefId(Long processDefineId)
			throws ProcessDefineServiceException {
		this.processInstanceDao.deleteById(processDefineId);

	}

}
