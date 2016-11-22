package com.zml.common.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
/**
 * easyui分页组件datagrid (PageBean)
 * @author ZML
 *
 */
public class Datagrid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Object>  rows= Collections.emptyList();
	private Long total=0L;
	
	public Datagrid(){
		
	}
	
	public Datagrid(Long total, List<Object> rows){
		this.rows = rows;
		this.total = total;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}
