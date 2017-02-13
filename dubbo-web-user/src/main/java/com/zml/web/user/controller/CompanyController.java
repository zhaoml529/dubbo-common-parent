package com.zml.web.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.zml.user.service.ICompanyService;

@RestController
public class CompanyController {

	@Autowired
	private ICompanyService companyService;
}
