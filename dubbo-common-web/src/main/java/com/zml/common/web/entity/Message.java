package com.zml.common.web.entity;

import org.springframework.http.HttpStatus;

public class Message {
	private String title = "提示";
	private String message = "";
	private Integer statusCode = HttpStatus.OK.value(); // HttpStatus.OK.value()
	private Object data = "";		// 需要传递的数据
	private Integer totalCount = 0;	// 分页总记录数

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
	
	public void setSuc() {
		this.message = "请求成功！";
	}
	
    public static Message create(Integer code, String message){  
        return new Message(code, message);  
    } 
    
    public static Message create(String title, Integer code, String message){  
    	return new Message(code, message);  
    } 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
