package com.dili.deliver.dao.impl;

import org.springframework.stereotype.Repository;

import com.dili.deliver.dao.IComPetitorDao;
import com.dili.deliver.entity.ComPetitor;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("comPetitorDao")
public class ComPetitorDaoImpl extends BaseDaoImpl<ComPetitor> implements IComPetitorDao{

}
