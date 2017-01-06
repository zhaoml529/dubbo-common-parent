package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Company;
import com.zml.user.exceptions.CompanyServiceException;

public interface ICompanyService {

	public Long addCompany(Company company) throws CompanyServiceException;
	
	public Long updateCompany(Company company) throws CompanyServiceException;
	
	public List<Company> findAll(Map<String, Object> map) throws CompanyServiceException;
}
