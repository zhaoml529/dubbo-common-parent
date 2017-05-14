package com.zml.log.aop.service;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zml.common.annotation.ServiceLog;
import com.zml.common.exceptions.ServiceException;
import com.zml.log.entity.SystemExceptionLog;
import com.zml.log.service.ISystemExceptionLogService;

/**
 * 业务层日志管理 
 * @author zhaomingliang
 * @date 2017年3月22日
 */
@Aspect
@Component
public class ServiceLogAspect {
	private  static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect. class);   
	
	@Autowired
	private ISystemExceptionLogService systemLogService;
	
	/**
	 * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	 * 匹配ServiceLog注解作为切入点，也可以不用注解直接匹配所有service实现类作为切入点，如：
	 * pointcut="execution(* com.zml.user.service.impl.*.*(..))")
	 */
	@Pointcut("@annotation(com.zml.common.annotation.ServiceLog)")
	public void aspectService() {
		
	}

	/**
	 * 
	 * @param joinPoint
	 * @param e
	 * @throws Exception 
	 */
	@AfterThrowing(pointcut = "aspectService()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) throws Exception {
		StringBuffer methodName = new StringBuffer();
		methodName.append(joinPoint.getTarget().getClass().getName()).append(".").append(joinPoint.getSignature().getName()).append("()");

		StringBuffer params = new StringBuffer();
		if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
             for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
                params.append(JSON.toJSONString(joinPoint.getArgs()[i])).append(";");
            }    
        }
		logger.info("=====异常通知开始=====");
		System.out.println("=====异常通知开始=====");    
        System.out.println("异常代码:" + ex.getClass().getName());    
        System.out.println("异常信息:" + ex.getMessage());    
        System.out.println("异常方法:" + methodName);    
        System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));    
        System.out.println("请求参数:" + params.toString());  
        System.out.println("---------------------------------------------------------------------------");
        
        if(ex instanceof ServiceException) {
        	System.out.println("code:"+((ServiceException) ex).getCode());
        	System.out.println("errMsg:"+((ServiceException) ex).getErrMsg());
        }
        
        String content = getServiceMethodDescription(joinPoint);
        SystemExceptionLog log = new SystemExceptionLog();
        log.setMethodName(methodName.toString());
        log.setContent(content);
        
        log.setErrorName(ex.getClass().getName());	// 异常名称
        log.setErrorMessage(ex.getMessage());		// 异常信息
        log.setParams(params.toString());
        if(ex instanceof ServiceException) {
        	log.setErrorCode(((ServiceException) ex).getCode());
        }
        this.systemLogService.addLog(log);
        
	}
	
	/**  
     * 获取注解中对方法的描述信息
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
	@SuppressWarnings("rawtypes")
    private String getServiceMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
		Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String content = "";
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {    
                	String value = method.getAnnotation(ServiceLog. class).content();    
                	content = value;
                    break;    
                }  
            }    
        }    
        return content;    
   }
	
}
