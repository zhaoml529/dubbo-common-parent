package com.zml.common.page;

import java.io.Serializable;

/**
 * 公共参数
 * @author zhao
 *
 */
public class Parameter<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7193164122478117559L;
	
	//模糊查询
	private String searchName;
	private String searchValue;
	//分页
	private Integer currPage;	// 当前第几页
	private Integer rows;		// 每页多少条数据
	private String sort; 
	private String order;
	
	private Page<T> page;
	
	public Parameter() {
		Page<T> page = new Page<T>();
		page.setPage(this.currPage);
		page.setRows(this.rows);
		this.page = page;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
	
}
