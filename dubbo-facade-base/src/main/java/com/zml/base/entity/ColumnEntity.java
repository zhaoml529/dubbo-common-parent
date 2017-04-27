package com.zml.base.entity;

import com.zml.common.entity.BaseEntity;

/**
 * 列的属性
 * @author zhaomingliang
 * @date 2017年4月14日
 */
public class ColumnEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3334309376130133115L;

	private String columnName;		// 列名
    
    private String dataType;		// 列名类型
    
    private String comments;		// 列名备注
    
    private String columnKey;		// 列KEY
    
    private String attrName;		// 属性名称(第一个字母大写)，如：user_name => UserName
    
    private String attrname;		// 属性名称(第一个字母小写)，如：user_name => userName
    
    private String attrType;		// 属性类型
    
    private String extra;			// auto_increment

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
    
}
