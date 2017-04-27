package com.zml.base.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.base.dao.ISysGeneratorDao;
import com.zml.base.entity.ColumnEntity;
import com.zml.base.entity.TableEntity;
import com.zml.base.service.ISysGeneratorService;
import com.zml.base.util.GenUtils;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements ISysGeneratorService {
	
	@Autowired
	private ISysGeneratorDao sysGeneratorDao;

	@Override
	public Page getTableList(Parameter<TableEntity> param) {
		param.initPage();	// 初始化分页参数
		Page page = this.sysGeneratorDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}

	@Override
	public TableEntity getTable(String tableName) {
		return sysGeneratorDao.getTable(tableName);
	}

	@Override
	public List<ColumnEntity> getColumns(String tableName) {
		return sysGeneratorDao.getColumns(tableName);
	}

	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			TableEntity table = getTable(tableName);
			//查询列信息
			List<ColumnEntity> columns = getColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
