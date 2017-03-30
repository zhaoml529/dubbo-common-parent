package com.zml.activiti.dao;

import java.util.List;
import java.util.Map;

import com.zml.activiti.entity.ProcessTask;
import com.zml.core.dao.BaseDao;

public interface IProcessTaskDao extends BaseDao<ProcessTask> {

	public void completeTask(String taskId);
	
	public List<Map<String, Object>> findRunningProcess(Map<String, Object> params);
	
	public void claimTask(String taskId, String staffId);
}
