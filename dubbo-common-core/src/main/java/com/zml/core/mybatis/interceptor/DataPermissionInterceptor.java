package com.zml.core.mybatis.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
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
				MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
				//拦截到的prepare方法参数是一个Connection对象
				Connection connection = (Connection)invocation.getArgs()[0];
				//获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
				String sql = boundSql.getSql();
				this.getUserDataPermission(sql, roleId);
				
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
	
	@SuppressWarnings("unchecked")
	private void getUserDataPermission(String sql, Long roleId) throws Exception {
		this.redisUtil = SpringContextUtil.getBean(RedisUtil.class);
		Map<Object, Object> map =this.redisUtil.getCacheMap(CacheConstant.DATA_PERMISSION_KEY);
		if(map != null && !map.isEmpty()) {
			Object dpObj = map.get(roleId.toString());
			Map<String, Object> dpMap = this.objectToMap(dpObj); 
			if(dpMap != null || !dpMap.isEmpty()) {
				
			}
		}
	}
	
	private Map<String, Object> objectToMap(Object obj) throws Exception {
		Map<String, Object> map = null;
		if(obj == null){ 
			return null;
		}
		map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

}
