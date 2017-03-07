package com.zml.common.web.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Message {
	private String title = "提示";
	private String message = "";
	private HttpStatus statusCode = HttpStatus.OK;
	private Object data = "";	//需要传递的数据
	
	private Map<String, Object> paramMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

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
