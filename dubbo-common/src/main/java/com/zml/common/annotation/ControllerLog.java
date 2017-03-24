package com.zml.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zml.common.enums.OperateLogTypeEnum;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

	String content() default "";		// 操作描述
	
	OperateLogTypeEnum operationType() ;	// 操作类型 OperateLogTypeEnum
}
