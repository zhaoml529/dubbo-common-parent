package com.zml.web.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;
import com.zml.user.entity.Company;
import com.zml.user.exceptions.CompanyServiceException;
import com.zml.user.service.ICompanyService;

@RestController
public class CompanyController extends BaseController {

	@Autowired
	private ICompanyService companyService;
	
	/**
	 * 公司详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	public Message getDetail(@PathVariable("id") Long id) {
		Message message = new Message();
		Company company = this.companyService.getById(id);
		message.setData(company);
		return message;
	}
	
	/**
	 * 分页显示公司列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public Message getCompanyPage(@RequestBody Parameter<Company> param) {
		Message message = new Message();
		Page page = this.companyService.getCompanyPage(param);
		message.setMessage("获取列表成功！");
        message.setData(page.getRecordList());
        message.setTotalCount(page.getTotalCount());
		return message;
	}
	
	/**
	 * 添加company
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public Message createCompany(@Valid @RequestBody Company company) {
		Message message = new Message();
		this.companyService.addCompany(company);
		message.setSuc();
		//super.logSave("添加公司信息成功！");
		return message;
	}
	
	/**
	 * 更新公司信息
	 * @param id
	 * @param company
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
	public Message updateCompany(@PathVariable("id") Long id, @Valid @RequestBody Company company, BindingResult result) {
		Message message = new Message();
		try {
			if(result.hasErrors()) {
				message.setValidFail(super.loadFieldError(result.getFieldErrors()));
			} else {
				company.setId(id);
				this.companyService.updateCompany(company);
				message.setSuc();
				//super.logUpdate("更新公司信息成功！");
			}
		} catch (CompanyServiceException e) {
			//super.logUpdateErr("更新公司信息失败！", e.getCode());
			throw e;
		}
		return message;
	}
	
	/**
	 * 逻辑删除公司信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
	public Message deleteCompany(@PathVariable("id") Long id) {
		Message message = new Message();
		try {
			this.companyService.getById(id);
			message.setSuc();
			//super.logDelete("删除公司信息成功！");
		} catch (CompanyServiceException e) {
			//super.logDeleteErr("删除公司信息失败！", e.getCode());
			throw e;
		}
		return message;
	}
	
}
