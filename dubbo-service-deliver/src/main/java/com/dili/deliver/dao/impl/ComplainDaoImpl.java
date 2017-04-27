package com.dili.deliver.dao.impl;

import org.springframework.stereotype.Repository;

import com.dili.deliver.dao.IComplainDao;
import com.dili.deliver.entity.Complain;
import com.zml.core.dao.impl.BaseDaoImpl;

/**
 * 投诉表
 * @description: 
 * @author: zhengrs
 * @date: 2017年4月19日 下午1:54:34
 */
@Repository("complainDao")
public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements IComplainDao {
	
}
