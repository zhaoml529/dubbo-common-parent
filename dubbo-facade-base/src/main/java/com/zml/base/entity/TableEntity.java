package com.zml.base.entity;
import java.util.List;

import com.zml.common.entity.BaseEntity;

/**
 * 表数据
 * @author zhaomingliang
 * @date 2017年4月14日
 */
public class TableEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5256691258630256685L;

	private String tableName;			// 表的名称
	
	private String comments;			// 表的备注
	
	private ColumnEntity pk;			// 表的主键
	
	private List<ColumnEntity> columns;	// 表的列名(不包含主键)
	
	private String className;			// 类名(第一个字母大写)，如：sys_user => SysUser
	
	private String classname;			// 类名(第一个字母小写)，如：sys_user => sysUser

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ColumnEntity getPk() {
		return pk;
	}

	public void setPk(ColumnEntity pk) {
		this.pk = pk;
	}

	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
