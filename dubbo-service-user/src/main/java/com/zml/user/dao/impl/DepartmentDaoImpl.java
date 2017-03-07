package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IDepartmentDao;
import com.zml.user.entity.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements
		IDepartmentDao {


}
