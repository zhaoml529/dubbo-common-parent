package com.zml.common.web.entity;

import org.springframework.http.HttpStatus;

public class Message {
	private String title = "提示";
	private String message = "";
	private HttpStatus statusCode = HttpStatus.OK;
	private Object data = "";	//需要传递的数据
	
	public void setSuc() {
		this.message = "请求成功！";
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
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
}
