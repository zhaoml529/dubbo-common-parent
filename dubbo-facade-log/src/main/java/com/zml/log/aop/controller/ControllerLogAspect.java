package com.zml.log.aop.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zml.common.annotation.ControllerLog;
import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.log.entity.UserOperateLog;
import com.zml.log.service.IUserOperateLogService;
import com.zml.user.service.IUserService;

/**
 * 控制层日志管理 
 * @author zhaomingliang
 * @date 2017年3月22日
 */
@Aspect
@Component
public class ControllerLogAspect {
	private  static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect. class);    

	@Autowired
	private IUserOperateLogService userOperateLogService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisUtil<Object> userRedis;
	
	/**
	 * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	 */
	@Pointcut("@annotation(com.zml.common.annotation.ControllerLog)")
	public void aspectController() {
		
	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspectController()")
	public  void before(JoinPoint joinPoint) throws Exception {
		StringBuffer methodName = new StringBuffer();
		methodName.append(joinPoint.getTarget().getClass().getName()).append(".").append(joinPoint.getSignature().getName()).append("()");
		logger.info("=====前置通知开始====="); 
		logger.info("请求方法:" + methodName.toString());
		logger.info("方法描述:" + getControllerMethodDescription(joinPoint));    
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
		String[] param = getControllerMethodDescription(joinPoint);
		UserOperateLog operateLog= new UserOperateLog();
		String ip = request.getRemoteAddr();
		String userId = (String) request.getAttribute(SystemConstant.CURRENT_USER_ID);
		if(StringUtils.isNotBlank(userId)) {
			Object user = this.userRedis.getCacheObject(CacheConstant.CURRENT_USER_ID + userId);
			if(user == null) {
				user = this.userService.getUserById(Long.valueOf(userId));
				this.userRedis.setCacheObject(CacheConstant.CURRENT_USER_ID + userId, user);
			}
			if(user != null) {
				Map<String, Object> userMap = this.object2Map(user);
				logger.info("请求人:" + userMap.get("userName"));    
				logger.info("请求IP:" + ip);    
				
				operateLog.setUserId(Long.valueOf(userMap.get("id").toString()));
				operateLog.setUserName(userMap.get("userName").toString());
				operateLog.setStaffNum(Long.valueOf(userMap.get("staffNum").toString()));
			}
		}
		operateLog.setMethodName(methodName.toString());
		operateLog.setContent(param[0]);	// 操作描述
		operateLog.setOperType(param[1]);	// 操作类型
		operateLog.setIp(ip);
		this.userOperateLogService.addLog(operateLog);
	}
	
	/**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
	@SuppressWarnings("rawtypes")
    private String[] getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
		Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String[] param = new String[2];
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {    
                	String content = method.getAnnotation(ControllerLog. class).content();    
                	OperateLogTypeEnum operationType = method.getAnnotation(ControllerLog. class).operationType();    
                	param[0] = content;
                	param[1] = operationType.getValue();
                    break;    
                }  
            }    
        }    
        logger.info(param.toString());
        return param;    
   }
	
   private Map<String, Object> object2Map(Object object){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Entry<String,Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map=new HashMap<String,Object>();
        for (Entry<String, Object> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
   }
	
}
