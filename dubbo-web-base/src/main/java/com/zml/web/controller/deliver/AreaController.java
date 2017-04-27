package com.zml.web.controller.deliver;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dili.deliver.entity.Area;
import com.dili.deliver.exceptions.AreaServiceException;
import com.dili.deliver.service.IAreaService;
import com.zml.common.annotation.ControllerLog;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.annotation.Permission;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;


/**
 * 片区控制器
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 16:55:16
 */
@RestController
@RequestMapping("/api")
public class AreaController extends BaseController {

	@Autowired
	private IAreaService areaService;
	
	/**
	 * 分页获取列表
	 * @param param
	 * @return
	 */
	@Permission("area:list")
	@RequestMapping(value = "/area/list", method = RequestMethod.POST)
	public Message list(@RequestBody Parameter<Area> param) {
		Message message = new Message();
		//super.setDataPermission(param.getParamMap());	// 设置数据权限
		Page page = this.areaService.getListPage(param);
		message.setMessage("获取列表成功！");
        message.setData(page.getRecordList());
        message.setTotalCount(page.getTotalCount());
        return message;
	}
	
	/**
	 * 根据省市区查询区域列表
	 * @param codes    省市区json串
	 * @return
	 */
	@Permission("area:list")
	@RequestMapping(value = "/area/ssq/list", method = RequestMethod.POST)
	public Message ssqList(@RequestParam("codes") String codes) {
		Message message = new Message();
		if(StringUtils.isNotBlank(codes)) {
			Map<String, Object> ssqStr = JSON.parseObject(codes, new TypeReference<Map<String, Object>>(){});
			List<Area> area = this.areaService.getList(ssqStr);
			message.setData(area);
		} else {
			throw AreaServiceException.QUERY_AREA_WITH_SSQ_ERR;
		}
		return message;
	}
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@ControllerLog(content = "查询详情信息", operationType = OperateLogTypeEnum.QUERYA)
	//@Permission("area:detail")
	@RequestMapping(value = "/area/{id}", method = RequestMethod.GET)
	public Message detail(@PathVariable("id") Long id) {
		Message message = new Message();
		Area area = this.areaService.getById(id);
		message.setData(area);
		return message;
		
	}
	
	/**
	 * 添加
	 * @param area
	 * @param result
	 * @return
	 */
	@ControllerLog(content = "添加片区", operationType = OperateLogTypeEnum.ADD)
	//@Permission("area:save")
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	public Message save(@Valid @RequestBody Area area, BindingResult result) {
		Message message = new Message();
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.areaService.save(area);
			message.setMessage("添加成功！");
			message.setSuc();
		}
		return message;
	}
	
	/**
	 * 更新
	 * @Valid验证前台传过来的参数是否合法
	 * @param id
	 * @param area
	 * @return
	 */
	@ControllerLog(content = "更新", operationType = OperateLogTypeEnum.UPDATE)
	@Permission("area:update")
	@RequestMapping(value = "/area", method = RequestMethod.PUT)
    public Message update(@Valid @RequestBody Area area, BindingResult result) {
		Message message = new Message();
		// 相关字段是否验证失败
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.areaService.update(area);
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
	@Permission("area:delete")
	@RequestMapping(value = "/area/{id}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("id") Long id) {
		Message message = new Message();
		this.areaService.delete(id);
		message.setMessage("删除成功！");
    	message.setSuc();
        return message;
    }
	
}
