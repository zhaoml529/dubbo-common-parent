package com.zml.activiti.service;

import java.util.List;
import java.util.Map;

import com.zml.activiti.entity.ProcessTask;
import com.zml.activiti.exceptions.ProcessTaskServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 用户任务接口
 * @author zhaomingliang
 *
 */
public interface IProcessTaskService {

	/**
	 * 添加
	 * @param processTask
	 * @return
	 * @throws Exception
	 */
	public void add(ProcessTask processTask) throws ProcessTaskServiceException;
	
	/**
	 * 更新
	 * @param processTask
	 * @throws Exception
	 */
	public void update(ProcessTask processTask) throws ProcessTaskServiceException;
	
	/**
	 * 通过id查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProcessTask findById(Long id) throws ProcessTaskServiceException;
	
	/**
	 * 通过taskId查询任务表
	 * @param taskId
	 * @return
	 */
	public ProcessTask findByTaskId(String taskId) throws ProcessTaskServiceException;
	
	/**
	 * 分页查询所有
	 * @param params
	 * @return
	 */
	public Page findAll(Parameter<ProcessTask> param) throws ProcessTaskServiceException;
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	public List<ProcessTask> findAll(Map<String, Object> params) throws ProcessTaskServiceException;
	
	/**
	 * @Description: 根据taskId完成任务
	 * @param taskId
	 */
	public void completeTask(String taskId) throws ProcessTaskServiceException;
	
	/**
	 * 查询流程进展
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findRunningProcess(Map<String, Object> params) throws ProcessTaskServiceException;
	
	/**
	 * 签收任务
	 * @param taskId
	 * @param staffId
	 */
	public void claimTask(String taskId, String staffId) throws ProcessTaskServiceException;
}
