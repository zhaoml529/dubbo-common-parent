package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.ICompanyDao;
import com.zml.user.entity.Company;
import com.zml.user.exceptions.CompanyServiceException;
import com.zml.user.service.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {
	
	@Autowired
	private ICompanyDao companyDao;

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addCompany(Company company) throws CompanyServiceException {
		return this.companyDao.insert(company);
	}

	public Long updateCompany(Company company) throws CompanyServiceException {
		return this.companyDao.update(company);
	}

	public List<Company> findAll(Map<String, Object> map) throws CompanyServiceException {
		List<Company> list = this.companyDao.getList(map);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
