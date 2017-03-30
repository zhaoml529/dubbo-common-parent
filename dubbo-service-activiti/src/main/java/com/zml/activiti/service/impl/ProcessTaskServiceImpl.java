package com.zml.activiti.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.activiti.dao.IProcessTaskDao;
import com.zml.activiti.entity.ProcessTask;
import com.zml.activiti.exceptions.ProcessTaskServiceException;
import com.zml.activiti.service.IProcessTaskService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@Service("processTaskService")
public class ProcessTaskServiceImpl implements IProcessTaskService {

	@Autowired
	private IProcessTaskDao processTaskDao;
	
	@Override
	public void add(ProcessTask processTask) throws ProcessTaskServiceException {
		this.processTaskDao.insert(processTask);

	}

	@Override
	public void update(ProcessTask processTask)
			throws ProcessTaskServiceException {
		this.processTaskDao.update(processTask);

	}

	@Override
	public ProcessTask findById(Long id) throws ProcessTaskServiceException {
		return this.processTaskDao.getById(id);
	}

	@Override
	public ProcessTask findByTaskId(String taskId)
			throws ProcessTaskServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskId", taskId);
		return this.processTaskDao.getBy(paramMap);
	}

	@Override
	public Page findAll(Parameter<ProcessTask> param)
			throws ProcessTaskServiceException {
		param.initPage();
		return this.processTaskDao.listPage(param.getPageParam(), param.getParamMap());
	}

	@Override
	public List<ProcessTask> findAll(Map<String, Object> params)
			throws ProcessTaskServiceException {
		return this.processTaskDao.getList(params);
	}

	@Override
	public void completeTask(String taskId) throws ProcessTaskServiceException {
		this.processTaskDao.completeTask(taskId);

	}

	@Override
	public List<Map<String, Object>> findRunningProcess(
			Map<String, Object> params) throws ProcessTaskServiceException {
		List<Map<String, Object>> list = this.processTaskDao.findRunningProcess(params);
		if(list != null && list.size() > 0) {
			return list;
		} else {
			return Collections.<Map<String, Object>> emptyList();
		}
	}

	@Override
	public void claimTask(String taskId, String staffId)
			throws ProcessTaskServiceException {
		this.processTaskDao.claimTask(taskId, staffId);

	}

}
