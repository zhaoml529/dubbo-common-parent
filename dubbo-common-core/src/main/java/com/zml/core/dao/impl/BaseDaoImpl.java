package com.zml.core.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.pool.DruidDataSource;
import com.zml.common.entity.BaseEntity;
import com.zml.common.exceptions.ServiceException;
import com.zml.common.page.Datagrid;
import com.zml.common.page.Page;
import com.zml.core.dao.BaseDao;

/**
 * 
 * @Description 数据访问层基础类
 * @author zhaomingliang
 * @date 2016年11月18日
 */

public class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T> {
	
	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_COUNT = "listCount";
	public static final String SQL_GET_LIST = "getList";
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private DruidDataSource druidDataSource;
	
	/**
	 * mybatis-spring-1.2.x以后源码取消了自动注入SqlSessionFactory 和 SqlSessionTemplate，此处手动注入一下
	 * 否则会报 Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required 异常
	 */
	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public long insert(T entity) {
		if (entity == null) {
			throw new RuntimeException("T is null");
		}

		int result = this.sessionTemplate.insert(getStatement(SQL_INSERT), entity);

		if (result <= 0) {
			throw ServiceException.DB_INSERT_RESULT_0;
		}

		if (entity != null && entity.getId() != null && result > 0) {
			return entity.getId();
		}

		return result;
	}

	@Override
	public long update(T entity) {
		if (entity == null) {
			throw new RuntimeException("T is null");
		}
		
		int result = this.sessionTemplate.update(getStatement(SQL_UPDATE), entity);

		if (result <= 0) {
			throw ServiceException.DB_UPDATE_RESULT_0;
		}

		if (entity != null && entity.getId() != null && result > 0) {
			return entity.getId();
		}

		return result;
	}

	@Override
	public long deleteById(long id) {
		return (long) this.sessionTemplate.delete(getStatement(SQL_GET_BY_ID), id);
	}

	@Override
	public T getById(long id) {
		return this.sessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}
	
	@Override
	public List<T> getList(Map<String, Object> paramMap) {
		return (List<T>) this.getList(paramMap, SQL_GET_LIST);
	}
	
	@Override
	public List<T> getList(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}

		return sessionTemplate.selectList(getStatement(sqlId), paramMap);
	}

	@Override
	public T getBy(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.isEmpty()) {
			return null;
		}

		return this.sessionTemplate.selectOne(getStatement(SQL_GET_LIST), paramMap);
	}

	@Override
	public Datagrid listPage(Page<T> pageParam, Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		
		int[] pageParams = pageParam.getPageParams();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		// List<Object> list = this.sessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap, new RowBounds(pageParams[0], pageParams[1]));
		List<Object> list = getSqlSession().selectList(getStatement(SQL_LIST_PAGE), paramMap, new RowBounds(pageParams[0], pageParams[1]));
		// 统计总记录数
		// Object countObject = this.sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE), paramMap);
		Object countObject = (Object) getSqlSession().selectOne(getStatement(SQL_LIST_COUNT), paramMap);
		Long count = Long.valueOf(countObject.toString());
		return new Datagrid(count, list);
	}

	@Override
	public Datagrid listPage(Page<T> pageParam, Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		
		int[] pageParams = pageParam.getPageParams();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = this.sessionTemplate.selectList(getStatement(sqlId), paramMap, new RowBounds(pageParams[0], pageParams[1]));
		// 统计总记录数
		Object countObject = this.sessionTemplate.selectOne(getStatement(sqlId), paramMap);
		Long count = Long.valueOf(countObject.toString());
		
		return new Datagrid(count, list);
	}

	public String getStatement(String sqlId) {

		String name = this.getClass().getName();

		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

		return sb.toString();
	}

	@Override
	public String getSeqNextValue(String seqName) {
		boolean isClosedConn = false;
		// 获取当前线程的连接
		Connection connection = this.sessionTemplate.getConnection();
		// 获取Mybatis的SQLRunner类
		SqlRunner sqlRunner = null;
		try {
			// 要执行的SQL
			String sql = "";
			// 数据库驱动类
			String driverClass = this.druidDataSource.getDriver().getClass().getName();
			// 不同的数据库,拼接SQL语句
			if (driverClass.equals("com.ibm.db2.jcc.DB2Driver")) {
				sql = "  VALUES " + seqName.toUpperCase() + ".NEXTVAL";
			}
			if (driverClass.equals("oracle.jdbc.OracleDriver")) {
				sql = "SELECT " + seqName.toUpperCase() + ".NEXTVAL FROM DUAL";
			}
			if (driverClass.equals("com.mysql.jdbc.Driver")) {
				sql = "SELECT  FUN_SEQ('" + seqName.toUpperCase() + "')";
			}
			// 如果状态为关闭,则需要从新打开一个连接
			if (connection.isClosed()) {
				connection = this.sqlSessionFactory.openSession().getConnection();
				isClosedConn = true;
			}
			sqlRunner = new SqlRunner(connection);
			Object[] args = {};
			// 执行SQL语句
			Map<String, Object> params = sqlRunner.selectOne(sql, args);
			for (Object o : params.values()) {
				return o.toString();
			}
			return null;
		} catch (Exception e) {
			throw ServiceException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		} finally {
			if (isClosedConn) {
				sqlRunner.closeConnection();
			}
		}
	}

}
