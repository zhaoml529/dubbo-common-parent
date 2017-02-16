package com.zml.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 实体公共类
 * @author zhaomingliang
 * @date 2016年11月11日
 */
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1612599078907464818L;
	
	private Long id;						// 主键id
	
	private Long version = 0L;			// 数据版本号-乐观锁
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createDate = new Date();	// 创建日期

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
