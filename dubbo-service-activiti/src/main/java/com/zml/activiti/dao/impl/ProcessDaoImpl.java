package com.zml.activiti.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.activiti.dao.IProcessDao;
import com.zml.activiti.entity.ProcessTask;
import com.zml.core.dao.impl.BaseDaoImpl;

@Repository("processDao")
public class ProcessDaoImpl extends BaseDaoImpl<ProcessTask> implements IProcessDao {

}
