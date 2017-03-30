package com.zml.activiti.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.activiti.dao.IProcessDefineDao;
import com.zml.activiti.entity.ProcessDefine;
import com.zml.activiti.exceptions.ProcessDefineServiceException;
import com.zml.activiti.service.IProcessDefineService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@Service("processDefineService")
public class ProcessDefineServiceImpl implements IProcessDefineService {
	
	@Autowired
	private IProcessDefineDao processDefineDao;

	@Override
	public Long add(ProcessDefine processDefine)
			throws ProcessDefineServiceException {
		return this.processDefineDao.insert(processDefine);
	}

	@Override
	public void update(ProcessDefine processDefine, Map<String, Object> params)
			throws ProcessDefineServiceException {
		this.processDefineDao.update(processDefine);
		/*if(params != null) {
			this.processInstanceService.deleteByDefId(processDefine.getId());
			
			String[] operationTypes = params.get("operationTypes").toString().split(",");
			String[] targetRefs = params.get("targetRefs").toString().split(",");
			int i=0;
			Long creatorId = 0L;
			if(ObjectUtils.notEmpty(params.get("creatorId"))){
				creatorId = Long.valueOf(params.get("creatorId").toString());
			}
			ProcessInstance processInstance = new ProcessInstance();
			for(String operationType : operationTypes) {
				processInstance.setModelId(processDefine.getModelId());
				processInstance.setProcDefId(processDefine.getId());
				processInstance.setOperationType(operationType);
				processInstance.setTargetRef(Long.valueOf(targetRefs[i++]));
				processInstance.setCreatorId(creatorId);
				this.processInstanceService.add(processInstance);
			}
		}*/

	}

	@Override
	public Page findAllDefine(Parameter<ProcessDefine> param)
			throws ProcessDefineServiceException {
		param.initPage();
		return this.processDefineDao.listPage(param.getPageParam(), param.getParamMap());
	}

	@Override
	public List<ProcessDefine> findAllDefine(Map<String, Object> params)
			throws ProcessDefineServiceException {
		return this.processDefineDao.getList(params);
	}

	@Override
	public ProcessDefine findById(Long id) throws ProcessDefineServiceException {
		return this.processDefineDao.getById(id);
	}

	@Override
	public void delete(Long id) throws ProcessDefineServiceException {
		this.processDefineDao.deleteById(id);

	}

	@Override
	public List<ProcessDefine> findRepeatByName(String taskName, Long modelId, Long id)
			throws ProcessDefineServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskName", taskName);
		paramMap.put("modelId", modelId);
		paramMap.put("id", id);
		List<ProcessDefine> list = this.processDefineDao.getList(paramMap);
		if(list != null && list.size() > 0) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
