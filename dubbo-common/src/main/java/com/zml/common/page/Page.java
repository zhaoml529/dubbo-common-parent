package com.zml.common.page;

/**
 * easyui 分页
 * page   当前第几页
 * rows   每页显示数据数
 * @author ZML
 *
 * @param <T>
 */
public class Page<T> {
	private Integer page;
	private Integer rows;
	
	public Page(){
		
	}
	
	public Page(Integer page, Integer rows){
		this.page = page;
		this.rows = rows;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * 计算分页
	 * @param total
	 * @return
	 */
	public int[] getPageParams() {
		if(this.page == null || this.page < 1){
			this.page = 1;
		}
		if(this.rows == null || this.rows < 1){
			this.rows = 10;
		}
		int offset = (this.page - 1) * this.rows;
		int limit = this.rows;
		
		return new int[]{offset, limit};
	}
	
}
