package com.zml.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component  
public final class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.ctx = applicationContext;
	}
	
    public static <T> T getBean(Class<T> clz) {
        return ctx.getBean(clz);
    }
    
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String paramString) {
        return (T) ctx.getBean(paramString);
    }
	
}
