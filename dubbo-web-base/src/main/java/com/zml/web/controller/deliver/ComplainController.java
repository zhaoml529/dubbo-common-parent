package com.zml.web.controller.deliver;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dili.deliver.entity.Complain;
import com.dili.deliver.service.IComplainService;
import com.zml.common.annotation.ControllerLog;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.annotation.Permission;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;


/**
 * 投诉
 * @description: 
 * @author: zhengrs
 * @date: 2017年4月19日 下午1:57:41
 */
@RestController
@RequestMapping("/api")
public class ComplainController extends BaseController {

	@Autowired
	private IComplainService complainService;
	
	/**
	 * 分页获取列表
	 * @param param
	 * @return
	 */
	@Permission("complain:list")
	@RequestMapping(value = "/complain/list", method = RequestMethod.POST)
	public Message list(@RequestBody Parameter<Complain> param) {
		Message message = new Message();
		super.setDataPermission(param.getParamMap());	// 设置数据权限
		Page page = this.complainService.getListPage(param);
		message.setMessage("获取列表成功！");
        message.setData(page.getRecordList());
        message.setTotalCount(page.getTotalCount());
        return message;
	}
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@ControllerLog(content = "查询详情信息", operationType = OperateLogTypeEnum.QUERYA)
	@Permission("complain:detail")
	@RequestMapping(value = "/complain/{id}", method = RequestMethod.GET)
	public Message detail(@PathVariable("id") Long id) {
		Message message = new Message();
		Complain complain = this.complainService.getById(id);
		message.setData(complain);
		return message;
		
	}
	
	/**
	 * 添加
	 * @param complain
	 * @param result
	 * @return
	 */
	@ControllerLog(content = "添加", operationType = OperateLogTypeEnum.ADD)
	@Permission("complain:save")
	@RequestMapping(value = "/complain", method = RequestMethod.POST)
	public Message save(@Valid @RequestBody Complain complain, BindingResult result) {
		Message message = new Message();
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.complainService.save(complain);
			message.setMessage("添加成功！");
			message.setSuc();
		}
		return message;
	}
	
	/**
	 * 更新
	 * @Valid验证前台传过来的参数是否合法
	 * @param id
	 * @param complain
	 * @return
	 */
	@ControllerLog(content = "更新", operationType = OperateLogTypeEnum.UPDATE)
	@Permission("complain:update")
	@RequestMapping(value = "/complain", method = RequestMethod.PUT)
    public Message update(@Valid @RequestBody Complain complain, BindingResult result) {
		Message message = new Message();
		// 相关字段是否验证失败
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.complainService.update(complain);
			message.setMessage("更新成功！");
			message.setSuc();
		}
        return message;
    }
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@ControllerLog(content = "删除", operationType = OperateLogTypeEnum.DELETE)
	@Permission("complain:delete")
	@RequestMapping(value = "/complain/{id}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("id") Long id) {
		Message message = new Message();
		this.complainService.delete(id);
		message.setMessage("删除成功！");
    	message.setSuc();
        return message;
    }
	
}
