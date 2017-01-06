package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Department;
import com.zml.user.exceptions.DepartmentServiceException;

public interface IDepartmentService {

	public Long addDepartment(Department department) throws DepartmentServiceException;
	
	public Long updateDepartment(Department department) throws DepartmentServiceException;
	
	public List<Department> findAll(Map<String, Object> map) throws DepartmentServiceException;
}	
