package com.zml.activiti.service;

import java.util.List;
import java.util.Map;

import com.zml.activiti.entity.ProcessModel;
import com.zml.activiti.exceptions.ProcessModelServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;


/**
 *
 * @Description 流程模型接口
 * @author zhaomingliang
 * @date 2016年9月2日
 */
public interface IProcessModelService {

	/**
	 * 添加
	 * @param processModel
	 */
	public Long add(ProcessModel processModel) throws ProcessModelServiceException;
	
	/**
	 * 修改
	 * @param processModel
	 */
	public void update(ProcessModel processModel) throws ProcessModelServiceException;
	
	/**
	 * 分页查询
	 * @param param
	 * @return
	 */
	public Page findAllModel(Parameter<ProcessModel> param) throws ProcessModelServiceException;
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	public List<ProcessModel> findAllModel(Map<String, Object> params) throws ProcessModelServiceException;
	
	/**
	 * 通过id查询
	 * @param modelId
	 * @return
	 */
	public ProcessModel findById(Long modelId) throws ProcessModelServiceException;
	
	/**
	 * 通过key查询
	 * @param modelId
	 * @return
	 */
	public ProcessModel findByKey(String processKey) throws ProcessModelServiceException;
	
	/**
	 * 部署流程
	 * @param modelId
	 */
	public void deployModel(Long modelId) throws ProcessModelServiceException;
	
	/**
	 * 通过processName和id查询重复的节点名称
	 * @param modelName
	 * @param modelId
	 * @return
	 */
	public List<ProcessModel> findRepeatByName(String modelName, Long modelId) throws ProcessModelServiceException;
	
	/**
	 * 逻辑删除
	 * @param mode
	 */
	public void deleteModel(Long modelId) throws ProcessModelServiceException;
}
