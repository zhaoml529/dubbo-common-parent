package com.dili.deliver.dao.impl;

import org.springframework.stereotype.Repository;

import com.dili.deliver.dao.IMerchantDao;
import com.dili.deliver.entity.Merchant;
import com.zml.core.dao.impl.BaseDaoImpl;

/**
 * 商户表实现类
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:31:40
 */
@Repository("merchantDao")
public class MerchantDaoImpl extends BaseDaoImpl<Merchant> implements IMerchantDao {
	
}
