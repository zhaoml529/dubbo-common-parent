package com.zml.base.service;

import java.util.List;

import com.zml.base.entity.ColumnEntity;
import com.zml.base.entity.TableEntity;
import com.zml.base.exceptions.GeneratorServiceException;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

/**
 * 代码生成器
 * @author zhaomingliang
 * @date 2017年4月14日
 */
public interface ISysGeneratorService {
	
	public Page getTableList(Parameter<TableEntity> param) throws GeneratorServiceException;
	
	public TableEntity getTable(String tableName) throws GeneratorServiceException;
	
	public List<ColumnEntity> getColumns(String tableName) throws GeneratorServiceException;
	
	/**
	 * 生成代码
	 */
	public byte[] generatorCode(String[] tableNames) throws GeneratorServiceException;
}
