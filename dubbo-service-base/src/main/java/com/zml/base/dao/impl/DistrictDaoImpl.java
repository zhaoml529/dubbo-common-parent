package com.zml.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.base.dao.IDistrictDao;
import com.zml.base.entity.District;
import com.zml.core.dao.impl.BaseDaoImpl;

/**
 * 省市区表实现类
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 17:54:41
 */
@Repository("districtDao")
public class DistrictDaoImpl extends BaseDaoImpl<District> implements IDistrictDao {
	
}
