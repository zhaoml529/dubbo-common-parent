package com.zml.user.enums;

/**
 * 数据权限枚举
 * @author zhaomingliang
 * @date 2017年4月5日
 */
public enum DataPermsiisonEnum {

	OWNED("查看自己的数据", 1),
	PART_OF_POST("所属岗位下的数据", 2),
	MANAGER_POST("所管理岗位下的数据", 3),
	PART_OF_DEPT("所属部门下的数据", 4),
	MANAGER_DEPT("所管理部门下的数据",5),
	PART_OF_CMPY("所属公司下的数据", 6),
	MANAGER_CMPY("所管理公司下的数据", 7),
	ALL("所有数据", 8),
	NONE("无", 9);
	
	private final String desc;
	
	private final int value;
	
	public String getDesc() {
		return desc;
	}

	public int getValue() {
		return value;
	}
	
	DataPermsiisonEnum(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}
	
	/**
	 * 取枚举的json字符串
	 */
	public static String getEnumJsonStr() {
		DataPermsiisonEnum[] enums = DataPermsiisonEnum.values();
		StringBuffer jsonBuff = new StringBuffer("[");
		for (DataPermsiisonEnum senum : enums) {
			if (!"[".equals(jsonBuff.toString())) {
				jsonBuff.append(",");
			}
			jsonBuff.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("',value:'").append(senum.getValue()).append("'}");
		}
		jsonBuff.append("]");
		return jsonBuff.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(DataPermsiisonEnum.getEnumJsonStr());
	}
}
