package com.zml.common.web.annotationMethod;

import java.lang.reflect.Method;

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

import com.zml.common.annotation.ControllerLog;
import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.service.IUserOperateLogService;
import com.zml.user.entity.User;
import com.zml.user.entity.UserOperateLog;


@Aspect
@Component
public class ControllerLogAspect {
	private  static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect. class);    

	@Autowired
	private IUserOperateLogService operateLogService;
	
	@Autowired
	private RedisUtil<User> userRedis;
	
	// Controller层切点 
	@Pointcut("@annotation(com.zml.common.annotation.ControllerLog)")
	public void controllerAspect() {
		
	}

	@Before("controllerAspect()")
	public  void doBefore(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
		String userId = (String) request.getAttribute(SystemConstant.CURRENT_USER_ID);
		if(StringUtils.isNotBlank(userId)) {
			User user = this.userRedis.getCacheObject(CacheConstant.CURRENT_USER_ID + userId);
			if(user != null) {
				String ip = request.getRemoteAddr();
				System.out.println("=====前置通知开始====="); 
				System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
				System.out.println("方法描述:" + getControllerMethodDescription(joinPoint).toString());    
	            System.out.println("请求人:" + user.getUserName());    
	            System.out.println("请求IP:" + ip);    
	            
	            String[] param = getControllerMethodDescription(joinPoint);
	            UserOperateLog operateLog= new UserOperateLog();
				operateLog.setUserId(user.getId());
				operateLog.setUserName(user.getUserName());
				operateLog.setStaffNum(user.getStaffNum());
				//operateLog.setOperateStatus(logStatusEnum.getValue());
				operateLog.setContent(param[0]);	// 操作描述
				operateLog.setOperType(param[1]);	// 操作类型
				operateLog.setIp(ip);
				this.operateLogService.addLog(operateLog);
			}
		}
	}
	
	/**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String[] getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String content = "";    
        String operationType = "";    
        String[] param = new String[2];
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {    
                	content = method.getAnnotation(ControllerLog. class).content();    
                	operationType = method.getAnnotation(ControllerLog. class).operationType();    
                	param[0] = content;
                	param[1] = operationType;
                    break;    
                }  
            }    
        }    
         return param;    
    }
	
}
