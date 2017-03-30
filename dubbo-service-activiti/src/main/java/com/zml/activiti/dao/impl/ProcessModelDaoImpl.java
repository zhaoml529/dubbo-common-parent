package com.zml.activiti.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.activiti.dao.IProcessModelDao;
import com.zml.activiti.entity.ProcessModel;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("processModelDao")
public class ProcessModelDaoImpl extends BaseDaoImpl<ProcessModel> implements
		IProcessModelDao {


}
