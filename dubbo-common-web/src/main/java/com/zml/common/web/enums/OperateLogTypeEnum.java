package com.zml.common.web.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static OperateLogTypeEnum getEnum(String value){
		OperateLogTypeEnum resultEnum = null;
		OperateLogTypeEnum[] enumAry = OperateLogTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(value.equals(enumAry[i].getValue())){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		OperateLogTypeEnum[] ary = OperateLogTypeEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		OperateLogTypeEnum[] ary = OperateLogTypeEnum.values();
		List list = new ArrayList();
		for(int i=0;i<ary.length;i++){
			Map<String,String> map = new HashMap<String,String>();
			map.put("value",String.valueOf(ary[i].getValue()));
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}
}
