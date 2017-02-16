package com.zml.common.web.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作日志状态
 * @author zhaomingliang
 * @date 2017年2月16日
 */
public enum OperateLogStatusEnum {
	SUCCESS("操作成功", 100), ERROR("操作失败", 101);
	
	private final String desc;
	
	private final int value;
	
	public String getDesc() {
		return desc;
	}

	public int getValue() {
		return value;
	}

	private OperateLogStatusEnum(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}
	
	public static OperateLogStatusEnum getEnum(int value){
		OperateLogStatusEnum resultEnum = null;
		OperateLogStatusEnum[] enumAry = OperateLogStatusEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue() == value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		OperateLogStatusEnum[] ary = OperateLogStatusEnum.values();
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
		OperateLogStatusEnum[] ary = OperateLogStatusEnum.values();
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
