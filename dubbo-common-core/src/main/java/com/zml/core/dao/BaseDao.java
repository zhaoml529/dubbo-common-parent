package com.zml.core.dao;

import java.util.List;
import java.util.Map;

import com.zml.common.page.Datagrid;
import com.zml.common.page.Page;

/**
 * 数据访问层统一接口
 * @Description
 * @author zhaomingliang
 * @date 2016年11月18日
 */
public interface BaseDao<T> {
	
	public long insert(T entity);
	
	public long update(T entity);
	
	public long deleteById(long id);
	
	public T getById(long id);
	
	public T getBy(Map<String, Object> paramMap);
	
	public List<T> getList(Map<String, Object> paramMap);
	
	public List<T> getList(Map<String, Object> paramMap, String sqlId);

	public Datagrid listPage(Page<T> pageParam, Map<String, Object> paramMap);

	public Datagrid listPage(Page<T> pageParam, Map<String, Object> paramMap, String sqlId);
	
	public String getSeqNextValue(String seqName);

}
