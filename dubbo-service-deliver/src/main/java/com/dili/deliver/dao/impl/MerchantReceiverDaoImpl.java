package com.dili.deliver.dao.impl;

import org.springframework.stereotype.Repository;

import com.dili.deliver.dao.IMerchantReceiverDao;
import com.dili.deliver.entity.MerchantReceiver;
import com.zml.core.dao.impl.BaseDaoImpl;

/**
 * 商户收货人关联表实现类
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 15:31:41
 */
@Repository("merchantReceiverDao")
public class MerchantReceiverDaoImpl extends BaseDaoImpl<MerchantReceiver> implements IMerchantReceiverDao {
	
}
