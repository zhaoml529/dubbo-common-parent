package com.zml.common.web.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class Message {
	private String title = "提示";
	private String message = "";
	private Integer statusCode = HttpStatus.OK.value(); // HttpStatus.OK.value()
	private Object data = "";		// 需要传递的数据
	private Integer totalCount = 0;	// 分页总记录数
	private List<FieldErrorMessage> fieldErrors = new ArrayList<FieldErrorMessage>();

	public Message() {
		
	}
	
	public Message(Integer code, String message) {
		this.statusCode = code;
		this.message = message;
	}
	
	public Message(String title, Integer code, String message) {
		this.title = title;
		this.statusCode = code;
		this.message = message;
	}
	
	public Message(Integer code, String message, List<FieldErrorMessage> fieldErrors) {
		this.statusCode = code;
		this.message = message;
		this.fieldErrors = fieldErrors;
	}
	
    public static Message create(Integer code, String message){  
        return new Message(code, message);  
    } 
    
    public static Message create(Integer code, String message, List<FieldErrorMessage> fieldErrors){  
    	return new Message(code, message, fieldErrors);  
    } 
    
    public static Message create(String title, Integer code, String message){  
    	return new Message(title, code, message);  
    } 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setSuc() {
		this.message = "请求成功！";
	}
	
	public void setValidFail(List<FieldErrorMessage> fieldErrors) {
		this.fieldErrors = fieldErrors;
		this.message = "参数验证失败！";
		this.statusCode = HttpStatus.BAD_REQUEST.value();	// 400 - Bad Request
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<FieldErrorMessage> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorMessage> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
}
