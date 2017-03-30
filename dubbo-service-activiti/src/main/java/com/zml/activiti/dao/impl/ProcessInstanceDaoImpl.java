package com.zml.activiti.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.activiti.dao.IProcessInstanceDao;
import com.zml.activiti.entity.ProcessInstance;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("processInstanceDao")
public class ProcessInstanceDaoImpl extends BaseDaoImpl<ProcessInstance>
		implements IProcessInstanceDao {

}
