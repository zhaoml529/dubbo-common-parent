package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Company;

public interface ICompanyService {

	public Long addCompany(Company company);
	
	public Long updateCompany(Company company);
	
	public List<Company> findAll(Map<String, Object> map);
}
