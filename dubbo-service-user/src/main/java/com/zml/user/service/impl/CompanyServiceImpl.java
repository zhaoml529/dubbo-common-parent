package com.zml.user.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.dao.ICompanyDao;
import com.zml.user.entity.Company;
import com.zml.user.exceptions.CompanyServiceException;
import com.zml.user.service.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {
	
	@Autowired
	private ICompanyDao companyDao;

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long addCompany(Company company) throws CompanyServiceException {
		if(isExist(company.getName())) {
			throw CompanyServiceException.COMPANY_IS_EXIST;
		}
		return this.companyDao.insert(company);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long updateCompany(Company company) throws CompanyServiceException {
		if(isExist(company.getName())) {
			throw CompanyServiceException.COMPANY_IS_EXIST;
		}
		return this.companyDao.update(company);
	}
	
	private boolean isExist(String name) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		Company c = this.companyDao.getBy(paramMap);
		if(c != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Company> findAll(Map<String, Object> map) throws CompanyServiceException {
		List<Company> list = this.companyDao.getList(map);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public Company getById(Long id) throws CompanyServiceException {
		Company company = this.companyDao.getById(id);
		if(company == null) {
			throw CompanyServiceException.COMPANY_NOT_EXIST;
		}
		return company;
	}

	@Override
	public Page getCompanyPage(Parameter<Company> param)
			throws CompanyServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.companyDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}

	@Override
	public Long deleteCompany(Long id) throws CompanyServiceException {
		Company c = this.getById(id);
		if(c == null) {
			throw CompanyServiceException.DELETE_COMPANY_ERR;
		}
		return this.companyDao.deleteById(id);
	}

}
