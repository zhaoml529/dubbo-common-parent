package com.zml.common.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zml.common.exceptions.ServiceException;
import com.zml.common.web.entity.Message;

/**
 * 全局异常处理器
 * 系统遇到异常，在程序中抛出，dao抛给Service，Service抛给Controller，Controller抛给前端控制器，前端控制器调用全局异常处理器
 * 全局异常处理器解析出异常类型：
 * 1.如果该异常类型是系统自定义异常(如：ServiceException)，直接取出异常信息，在错误界面展示。
 * 2.如果该异常类型不是系统自定义异常，则需要返回一个友好的异常信息供前台展示。
 * @author zhaomingliang
 * @date 2017年3月8日
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/** 
     * 400 - Bad Request 
     */  
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(HttpMessageNotReadableException.class)  
	@ResponseBody
	public Message handleHttpMessageNotReadableException (HttpMessageNotReadableException e) {
		logger.error("参数解析失败 - could_not_read_json", e);  
		return Message.create(HttpStatus.BAD_REQUEST.value(), "服务异常，请联系管理员！");
	}
	
	/** 
     * 405 - Method Not Allowed 
     */  
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)  
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)  
    @ResponseBody
    public Message handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {  
        logger.error("不支持当前请求方法 - request_method_not_supported", e);  
        return Message.create(HttpStatus.METHOD_NOT_ALLOWED.value(), "服务异常，请联系管理员！");
    }  
    
    /** 
     * 415 - Unsupported Media Type 
     */  
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)  
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)  
    @ResponseBody
    public Message handleHttpMediaTypeNotSupportedException(Exception e) {  
        logger.error("不支持当前媒体类型 - content_type_not_supported", e);  
        return Message.create(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "服务异常，请联系管理员！");
    }  
  
    /** 
     * 500 - Internal Server Error 
     */  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(ServiceException.class)  
    @ResponseBody
    public Message serviceException(ServiceException e) {  
        logger.error("业务数据异常", e);  
        return Message.create(e.getCode(), e.getMessage());
    } 
    
    /** 
     * 500 - Internal Server Error 
     */  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(Exception.class)  
    @ResponseBody
    public Message handleException(Exception e) {  
    	logger.error("服务运行异常", e);  
    	return Message.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务异常，请联系管理员！");
    }  
}
