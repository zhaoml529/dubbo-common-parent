package com.zml.base.dao;

import java.util.List;

import com.zml.base.entity.ColumnEntity;
import com.zml.base.entity.TableEntity;
import com.zml.core.dao.BaseDao;

/**
 * 代码生成器
 * @author zhaomingliang
 * @date 2017年4月14日
 */
public interface ISysGeneratorDao extends BaseDao<TableEntity> {
	
	public TableEntity getTable(String tableName);
	
	public List<ColumnEntity> getColumns(String tableName);
}
