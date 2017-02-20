package com.zml.common.web.enums;


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
	
}
