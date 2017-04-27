package com.zml.web.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zml.base.entity.TableEntity;
import com.zml.base.service.ISysGeneratorService;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.entity.Message;
import com.zml.common.web.xss.XssHttpServletRequestWrapper;

/**
 * 代码生成器
 * @author zhao
 *
 */
@RestController
@RequestMapping("/api/sys/generator")
public class SysGeneratorController {
	@Autowired
	private ISysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public Message list(@RequestBody Parameter<TableEntity> param){
		Message message = new Message();
		Page page = this.sysGeneratorService.getTableList(param);
		message.setMessage("获取列表成功！");
        message.setData(page.getRecordList());
        message.setTotalCount(page.getTotalCount());
        return message;
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] tableNames = new String[]{};
		//获取表名，不进行xss过滤
		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
		String tables = orgRequest.getParameter("tables");
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		
		byte[] data = this.sysGeneratorService.generatorCode(tableNames);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"dubbo.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
}
