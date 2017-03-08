package com.zml.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @param <T>
 */
public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -920616597516144699L;
	
	private Integer currPage;			// 当前第几页
	private Integer numPage;			// 每页显示数据数
	
	private Integer totalCount; 		// 总记录数
	private List<Object> recordList; 	// 本页的数据列表
	
	// 计算
	private Integer pageCount; 			// 总页数
	private Integer beginPageIndex; 	// 页码列表的开始索引（包含）
	private Integer endPageIndex; 		// 页码列表的结束索引（包含）
	
	public Page() {
		
	}
	
	public Page(Integer currPage, Integer numPage, Integer totalCount, List<Object> recordList) {
		this.currPage = currPage;
		this.numPage = numPage;
		this.totalCount = totalCount;
		this.recordList = recordList;
		
		// 计算总页码
		this.pageCount = (totalCount + numPage - 1) / numPage;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (this.pageCount <= 10) {
			this.beginPageIndex = 1;
			this.endPageIndex = this.pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			this.beginPageIndex = currPage - 4;
			this.endPageIndex = currPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (this.beginPageIndex < 1) {
				this.beginPageIndex = 1;
				this.endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (this.endPageIndex > this.pageCount) {
				this.endPageIndex = this.pageCount;
				this.beginPageIndex = this.pageCount - 10 + 1;
			}
		}
	}
	
	public Page(Integer currPage, Integer numPage){
		this.currPage = currPage;
		this.numPage = numPage;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<Object> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Object> recordList) {
		this.recordList = recordList;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(Integer beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public Integer getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(Integer endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}
