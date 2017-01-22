package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IDepartmentDao;
import com.zml.user.entity.Department;
import com.zml.user.exceptions.DepartmentServiceException;
import com.zml.user.service.IDepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addDepartment(Department department)
			throws DepartmentServiceException {
		return this.departmentDao.insert(department);
	}

	public Long updateDepartment(Department department)
			throws DepartmentServiceException {
		return this.departmentDao.update(department);
	}

	public List<Department> findAll(Map<String, Object> map)
			throws DepartmentServiceException {
		List<Department> list = this.departmentDao.getList(map);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
