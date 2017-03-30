package com.zml.activiti.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.activiti.dao.IProcessDefineDao;
import com.zml.activiti.entity.ProcessDefine;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("processDefindDao")
public class ProcessDefineDaoImpl extends BaseDaoImpl<ProcessDefine> implements
		IProcessDefineDao {

}
