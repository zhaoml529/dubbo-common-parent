package com.zml.core.mybatis.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.utils.ReflectUtil;
import com.zml.common.utils.SpringContextUtil;
import com.zml.common.utils.cache.redis.RedisUtil;

@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class DataPermissionInterceptor implements Interceptor {
	
	private RedisUtil<Object> redisUtil;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		System.out.println(handler.getBoundSql().getSql());
		System.out.println(invocation.getArgs());
		this.getUserDataPermission("", 1L);
		//通过反射获取到当前RoutingStatementHandler对象的delegate属性
        StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
        //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
        BoundSql boundSql = delegate.getBoundSql();
        //拿到当前绑定Sql的参数对象，就是在调用对应的Mapper映射语句时所传入的参数对象
        Object paramObj = boundSql.getParameterObject();
		if(paramObj instanceof HashMap<?, ?>) {
			Boolean dataPermission = (Boolean)((HashMap<?, ?>)paramObj).get(SystemConstant.DATA_PERMISSION); 		// 查询是否走数据权限
			Long roleId = (Long)((HashMap<?, ?>)paramObj).get(SystemConstant.DATA_PERMISSION_ROLE_ID); 				// 根据用户角色id查询数据权限
			if(dataPermission != null && dataPermission && roleId != null) {
				//通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
				//MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
				//拦截到的prepare方法参数是一个Connection对象
				//Connection connection = (Connection)invocation.getArgs()[0];
				//获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
				String sql = boundSql.getSql().toLowerCase().trim();
				//获取拼装好的sql
				String newSql = this.getUserDataPermission(sql, roleId);
				//利用反射设置当前BoundSql对应的sql属性为新的sql语句
		        ReflectUtil.setFieldValue(boundSql, "sql", newSql);
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	
	/**
	 * 从缓存中取出角色数据权限数据
	 * @param sql
	 * @param roleId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String getUserDataPermission(String sql, Long roleId) throws Exception {
		this.redisUtil = SpringContextUtil.getBean(RedisUtil.class);
		Map<Object, Object> map =this.redisUtil.getCacheMap(CacheConstant.DATA_PERMISSION_KEY);
		if(map != null && !map.isEmpty()) {
			Object dpObj = map.get(roleId.toString());
			Map<String, Object> dpMap = this.objectToMap(dpObj); 
			if(dpMap != null && !dpMap.isEmpty()) {
				sql = this.getPermissionSql(sql, dpMap);
			}
		}
		return sql;
	}
	
	/**
	 * 组装sql
	 * @param sql
	 * @param dpMap
	 * @return
	 */
	private String getPermissionSql(String sql, Map<String, Object> dpMap) {
		StringBuffer sqlBuffer = new StringBuffer();
		String sql1 = "";
		String sql2 = "";
		int limitIndex = sql.indexOf("limit");
		if(limitIndex != -1) {
			sql1 = sql.substring(0, limitIndex);
			sql2 = sql.substring(limitIndex);
		} else {
			sql1 = sql;
		}
		
		if(dpMap.get("postId") != null && StringUtils.isNotBlank(dpMap.get("postId").toString())) {	// 岗位下的数据
			String postIds = dpMap.get("postId").toString();
			sqlBuffer.append("select dprs.* from (").append(sql1).append(") dprs ");
			sqlBuffer.append("left join tb_user tuser on tuser.id = dprs.createUserId and (");
			for(String postId : postIds.split(",")) {
				sqlBuffer.append(" tuser.postId = ").append(postId).append(" or");
			}
			sqlBuffer.delete(sqlBuffer.lastIndexOf("or"), sqlBuffer.length()).append(") ");
			sqlBuffer.append(sql2);
		} else if(dpMap.get("deptId") != null && StringUtils.isNotBlank(dpMap.get("deptId").toString())) {
			String deptIds = dpMap.get("deptId").toString();
			sqlBuffer.append("select dprs.* from (").append(sql1).append(") dprs ");
			sqlBuffer.append("left join tb_user tuser on tuser.id = dprs.createUserId and (");
			for(String deptId : deptIds.split(",")) {
				sqlBuffer.append(" tuser.deptId = ").append(deptId).append(" or");
			}
			sqlBuffer.delete(sqlBuffer.lastIndexOf("or"), sqlBuffer.length()).append(") ");
			sqlBuffer.append(sql2);
		} else if(dpMap.get("companyId") != null && StringUtils.isNotBlank(dpMap.get("companyId").toString())) {
			String companyIds = dpMap.get("companyId").toString();
			sqlBuffer.append("select dprs.* from (").append(sql1).append(") dprs ");
			sqlBuffer.append("left join tb_user tuser on tuser.id = dprs.createUserId and (");
			for(String companyId : companyIds.split(",")) {
				sqlBuffer.append(" tuser.companyId = ").append(companyId).append(" or");
			}
			sqlBuffer.delete(sqlBuffer.lastIndexOf("or"), sqlBuffer.length()).append(") ");
			sqlBuffer.append(sql2);
		} else if(dpMap.get("allData") != null && StringUtils.isNotBlank(dpMap.get("allData").toString())) {
			sqlBuffer.append(sql);
		} else {
			sqlBuffer.append(sql);
		}
		return sqlBuffer.toString();
	}
	
	/**
	 * 将obj转换成map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> objectToMap(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(obj == null){ 
			return null;
		}
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

}
