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

	private static final long serialVersionUID = 1L;
	private Long id;						// 主键id
	private Integer version = 0;			// 数据版本号-乐观锁
	private Date createTime = new Date();	// 创建日期

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
