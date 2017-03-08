package com.zml.common.page;

import java.io.Serializable;

/**
 * 分页参数传递工具类 
 * @author zhaomingliang
 * @date 2017年3月8日
 */
public class PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246850794153672314L;
	
	private Integer currPage; 		// 当前页数
	private Integer numPage; 		// 每页记录数
	
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

	/**
	 * 计算分页
	 * @param total
	 * @return
	 */
	public int[] getPageParams() {
		if(this.currPage == null || this.currPage < 1){
			this.currPage = 1;
		}
		if(this.numPage == null || this.numPage < 1){
			this.numPage = 10;
		}
		int offset = (this.currPage - 1) * this.numPage;
		int limit = this.numPage;
		
		return new int[]{offset, limit};
	}
}
