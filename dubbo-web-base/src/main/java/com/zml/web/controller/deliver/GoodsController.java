package com.zml.web.controller.deliver;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dili.deliver.entity.Goods;
import com.dili.deliver.service.IGoodsService;
import com.zml.common.annotation.ControllerLog;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.annotation.Permission;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;


/**
 * 商品表
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-17 18:24:50
 */
@RestController
@RequestMapping("/api")
public class GoodsController extends BaseController {

	@Autowired
	private IGoodsService goodsService;
	
	/**
	 * 分页获取列表
	 * @param param
	 * @return
	 */
	@Permission("goods:list")
	@RequestMapping(value = "/goods/list", method = RequestMethod.POST)
	public Message list(@RequestBody Parameter<Goods> param) {
		Message message = new Message();
		//super.setDataPermission(param.getParamMap());	// 设置数据权限
		Page page = this.goodsService.getListPage(param);
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
	@Permission("goods:detail")
	@RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
	public Message detail(@PathVariable("id") Long id) {
		Message message = new Message();
		Goods goods = this.goodsService.getById(id);
		message.setData(goods);
		return message;
		
	}
	
	/**
	 * 添加
	 * @param goods
	 * @param result
	 * @return
	 */
	@ControllerLog(content = "添加", operationType = OperateLogTypeEnum.ADD)
	@Permission("goods:save")
	@RequestMapping(value = "/goods", method = RequestMethod.POST)
	public Message save(@Valid @RequestBody Goods goods, BindingResult result) {
		Message message = new Message();
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.goodsService.save(goods);
			message.setMessage("添加成功！");
			message.setSuc();
		}
		return message;
	}
	
	/**
	 * 更新
	 * @Valid验证前台传过来的参数是否合法
	 * @param id
	 * @param goods
	 * @return
	 */
	@ControllerLog(content = "更新", operationType = OperateLogTypeEnum.UPDATE)
	@Permission("goods:update")
	@RequestMapping(value = "/goods", method = RequestMethod.PUT)
    public Message update(@Valid @RequestBody Goods goods, BindingResult result) {
		Message message = new Message();
		// 相关字段是否验证失败
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.goodsService.update(goods);
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
	@Permission("goods:delete")
	@RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("id") Long id) {
		Message message = new Message();
		this.goodsService.delete(id);
		message.setMessage("删除成功！");
    	message.setSuc();
        return message;
    }
	
}
