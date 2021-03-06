package com.zml.core.dao;

import java.util.List;
import java.util.Map;

import com.zml.common.page.Page;
import com.zml.common.page.PageParam;

/**
 * 数据访问层统一接口
 * @Description
 * @author zhaomingliang
 * @date 2016年11月18日
 */
public interface BaseDao<T> {
	
	public long insert(T entity);
	
	public long insert(List<T> list);
	
	public long update(T entity);
	
	public long deleteById(long id);
	
	public T getById(long id);
	
	public T getBy(Map<String, Object> paramMap);
	
	public T getBy(Map<String, Object> paramMap, String sqlId);
	
	public List<T> getList(Map<String, Object> paramMap);
	
	public List<T> getList(Map<String, Object> paramMap, String sqlId);

	public Page listPage(PageParam pageParam, Map<String, Object> paramMap);

	public Page listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);
	
	public String getSeqNextValue(String seqName);

}
