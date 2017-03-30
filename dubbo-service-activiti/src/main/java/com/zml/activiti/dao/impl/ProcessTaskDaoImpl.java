package com.zml.activiti.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zml.activiti.dao.IProcessTaskDao;
import com.zml.activiti.entity.ProcessTask;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("processTaskDao")
public class ProcessTaskDaoImpl extends BaseDaoImpl<ProcessTask> implements
		IProcessTaskDao {

	@Override
	public void completeTask(String taskId) {
		this.getSessionTemplate().update(this.getStatement("completeTask"), taskId);
	}

	@Override
	public List<Map<String, Object>> findRunningProcess(
			Map<String, Object> params) {
		return this.getSessionTemplate().selectList(this.getStatement("findRunningProcess"), params);
	}

	@Override
	public void claimTask(String taskId, String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskId", taskId);
		params.put("staffId", staffId);
		this.getSessionTemplate().update(this.getStatement("claimTask"), params);
	}


}
