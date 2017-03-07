package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.ICompanyDao;
import com.zml.user.entity.Company;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements ICompanyDao {


}
