package com.zml.web.controller.base;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zml.base.entity.District;
import com.zml.base.service.IDistrictService;
import com.zml.common.annotation.ControllerLog;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.annotation.Permission;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;


/**
 * 省市区表控制器
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 17:54:41
 */
@RestController
@RequestMapping("/api")
public class DistrictController extends BaseController {

	@Autowired
	private IDistrictService districtService;
	
	/**
	 * 分页获取列表
	 * @param param
	 * @return
	 */
	@Permission("district:list")
	@RequestMapping(value = "/district/list", method = RequestMethod.POST)
	public Message list(@RequestBody Parameter<District> param) {
		Message message = new Message();
		//super.setDataPermission(param.getParamMap());	// 设置数据权限
		Page page = this.districtService.getListPage(param);
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
	@Permission("district:detail")
	@RequestMapping(value = "/district/{id}", method = RequestMethod.GET)
	public Message detail(@PathVariable("id") Long id) {
		Message message = new Message();
		District district = this.districtService.getById(id);
		message.setData(district);
		return message;
		
	}
	
	/**
	 * 添加
	 * @param district
	 * @param result
	 * @return
	 */
	@ControllerLog(content = "添加", operationType = OperateLogTypeEnum.ADD)
	@Permission("district:save")
	@RequestMapping(value = "/district", method = RequestMethod.POST)
	public Message save(@Valid @RequestBody District district, BindingResult result) {
		Message message = new Message();
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.districtService.save(district);
			message.setMessage("添加成功！");
			message.setSuc();
		}
		return message;
	}
	
	/**
	 * 更新
	 * @Valid验证前台传过来的参数是否合法
	 * @param id
	 * @param district
	 * @return
	 */
	@ControllerLog(content = "更新", operationType = OperateLogTypeEnum.UPDATE)
	@Permission("district:update")
	@RequestMapping(value = "/district", method = RequestMethod.PUT)
    public Message update(@Valid @RequestBody District district, BindingResult result) {
		Message message = new Message();
		// 相关字段是否验证失败
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.districtService.update(district);
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
	@Permission("district:delete")
	@RequestMapping(value = "/district/{id}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("id") Long id) {
		Message message = new Message();
		this.districtService.delete(id);
		message.setMessage("删除成功！");
    	message.setSuc();
        return message;
    }
	
}
