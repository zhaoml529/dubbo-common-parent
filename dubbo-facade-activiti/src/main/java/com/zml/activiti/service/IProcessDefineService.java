package com.zml.activiti.service;

import java.util.List;
import java.util.Map;

import com.zml.activiti.entity.ProcessDefine;
import com.zml.activiti.exceptions.ProcessDefineServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 流程定义 
 * @author zhaomingliang
 * @date 2017年3月30日
 */
public interface IProcessDefineService {

	/**
	 * 添加
	 * @param processDefine
	 */
	public Long add(ProcessDefine processDefine) throws ProcessDefineServiceException;
	
	/**
	 * 修改
	 * @param processDefine
	 * @param params
	 */
	public void update(ProcessDefine processDefine, Map<String, Object> params) throws ProcessDefineServiceException;
	
	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Page findAllDefine(Parameter<ProcessDefine> param) throws ProcessDefineServiceException;
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	public List<ProcessDefine> findAllDefine(Map<String, Object> params) throws ProcessDefineServiceException;
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public ProcessDefine findById(Long id) throws ProcessDefineServiceException;
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id) throws ProcessDefineServiceException;
	
	/**
	 * 通过taskName和id查询重复项
	 */
	public List<ProcessDefine> findRepeatByName(String taskName, Long modelId, Long id) throws ProcessDefineServiceException;
	
	
}
