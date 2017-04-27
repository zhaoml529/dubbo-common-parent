package com.zml.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.dao.IDepartmentDao;
import com.zml.user.entity.Department;
import com.zml.user.exceptions.DepartmentServiceException;
import com.zml.user.service.IDepartmentService;


@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;
	
	@Override
	public Department getById(Long id) throws DepartmentServiceException {
		return departmentDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Department> param) throws DepartmentServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.departmentDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Department department) throws DepartmentServiceException {
		return this.departmentDao.insert(department);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Department department) throws DepartmentServiceException {
		this.departmentDao.update(department);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws DepartmentServiceException {
		this.departmentDao.deleteById(id);
	}
	
}
