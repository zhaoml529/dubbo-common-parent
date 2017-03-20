package com.zml.common.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zml.common.constant.SystemConstant;

/**
 * 自定义注解
 * @Target 用于描述注解的使用范围（即：被描述的注解可以用在什么地方）。
 * 		ElementType取值：CONSTRUCTOR(用于描述构造器)、METHOD（用于描述方法）、PACKAGE(用于描述包)、PARAMETER(用于描述参数)、TYPE(用于描述类、接口(包括注解类型) 或enum声明)等
 * @Retention 表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）。
 * 		RetentionPolicy取值：SOURCE:在源文件中有效（即源文件保留）、CLASS:在class文件中有效（即class保留）、RUNTIME:在运行时有效（即运行时保留）
 * @Documented 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 * @Inherited 阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 * http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 * @author zhao
 *
 */

@Target(ElementType.PARAMETER)	
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

	/**
	 * 当前用户在request中的id
	 * @return
	 */
	String value() default SystemConstant.CURRENT_USER_ID;
}
