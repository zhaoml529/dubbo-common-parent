package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Department;

public interface IDepartmentService {

	public Long addDepartment(Department department);
	
	public Long updateDepartment(Department department);
	
	public List<Department> findAll(Map<String, Object> map);
}	
