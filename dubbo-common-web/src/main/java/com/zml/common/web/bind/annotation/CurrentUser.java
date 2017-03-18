package com.zml.common.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zml.common.constant.SystemConstant;

/**
 * 自定义注解
 * 从
 * @author zhao
 *
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

	/**
	 * 当前用户在request中的id
	 * @return
	 */
	String value() default SystemConstant.CURRENT_USER_ID;
}
