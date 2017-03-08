package com.zml.common.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
	
	// 分页
	private Integer currPage;	// 当前第几页
	private Integer numPage;	// 每页多少条数据
	
	private String sort; 
	private String order;
	
	// 查询条件
	private Map<String, Object> paramMap = new HashMap<String, Object>();
	
	// 分页参数
	private PageParam pageParam;
	
	public Parameter() {

	}

	public void initPage() {
		PageParam page = new PageParam();
		page.setCurrPage(this.currPage);
		page.setNumPage(this.numPage);
		this.pageParam = page;
	}
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getNumPage() {
		return numPage;
	}

	public void setNumPage(Integer numPage) {
		this.numPage = numPage;
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

	public PageParam getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam pageParam) {
		this.pageParam = pageParam;
	}

}
