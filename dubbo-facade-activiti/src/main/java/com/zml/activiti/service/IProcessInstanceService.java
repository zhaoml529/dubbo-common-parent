package com.zml.activiti.service;

import java.util.List;
import java.util.Map;

import com.zml.activiti.entity.ProcessInstance;
import com.zml.activiti.exceptions.ProcessDefineServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 流程实例接口
 * @author zhaomingliang
 * @date 2017年3月30日
 */
public interface IProcessInstanceService {
	/**
	 * 添加
	 * @param processInstance
	 */
	public void add(ProcessInstance processInstance) throws ProcessDefineServiceException;
	
	/**
	 * 修改
	 * @param processInstance
	 */
	public void update(ProcessInstance processInstance) throws ProcessDefineServiceException;
	
	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Page findAllInstance(Parameter<ProcessInstance> param) throws ProcessDefineServiceException;
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	public List<ProcessInstance> findAllInstance(Map<String, Object> params) throws ProcessDefineServiceException;
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public ProcessInstance findById(Long id) throws ProcessDefineServiceException;
	
	/**
	 * 根据processDefineId删除
	 * @param processDefineId
	 */
	public void deleteByDefId(Long processDefineId) throws ProcessDefineServiceException;
}
