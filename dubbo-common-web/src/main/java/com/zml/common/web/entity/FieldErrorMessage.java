package com.zml.common.web.entity;

import java.io.Serializable;

/**
 * HibernateValidator - 自定义字段错误信息
 * @Description
 * @author zhaomingliang
 * @date 2017年3月9日
 */
public class FieldErrorMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1810985011919541119L;

	private String entryName = "";		// 验证的实体名称
	
	private String fieldName = "";		// 字段名称
	
	private String errorMessage = "";	// 验证错误信息

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
