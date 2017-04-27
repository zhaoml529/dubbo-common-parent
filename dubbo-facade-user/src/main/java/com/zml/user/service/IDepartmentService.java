package com.zml.user.service;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.entity.Department;
import com.zml.user.exceptions.DepartmentServiceException;

/**
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 08:38:41
 */
public interface IDepartmentService {
	
	public Department getById(Long id) throws DepartmentServiceException;
	
	public Page getListPage(Parameter<Department> param) throws DepartmentServiceException;
	
	public Long save(Department department) throws DepartmentServiceException;
	
	public void update(Department department) throws DepartmentServiceException;
	
	public void delete(Long id) throws DepartmentServiceException;
	
}
