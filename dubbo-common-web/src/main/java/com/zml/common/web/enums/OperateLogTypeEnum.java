package com.zml.common.web.enums;


public enum OperateLogTypeEnum {
	ADD("增加", "ADD"), UPDATE("修改", "UPDATE"), DELETE("删除", "DELETE"), QUERYA("查询", "QUERY"), LOGIN("登录", "LOGIN"), LOGOUT("登出", "LOGOUT");
	
	private final String desc;
	
	private final String value;
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}

	private OperateLogTypeEnum(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
}
