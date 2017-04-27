package com.zml.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zml.base.dao.ISysGeneratorDao;
import com.zml.base.entity.ColumnEntity;
import com.zml.base.entity.TableEntity;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("sysGeneratorDao")
public class SysGeneratorDaoImpl extends BaseDaoImpl<TableEntity>
		implements ISysGeneratorDao {

	@Override
	public TableEntity getTable(String tableName) {
		return this.getSessionTemplate().selectOne(this.getStatement("getTable"), tableName);
	}

	@Override
	public List<ColumnEntity> getColumns(String tableName) {
		return this.getSessionTemplate().selectList(this.getStatement("getColumns"), tableName);
	}
}
