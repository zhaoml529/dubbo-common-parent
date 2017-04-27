package com.zml.web.controller.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zml.common.annotation.ControllerLog;
import com.zml.common.enums.OperateLogTypeEnum;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.web.annotation.CurrentUser;
import com.zml.common.web.annotation.Permission;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

/**
 * @Permission("user:view")	
 * 规则："资源标识符:操作" - 不支持通配符
 * 上面表示拥有user资源的view操作权限
 * 
 * @author zhaomingliang
 * @date 2017年3月20日
 */
@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 用户详情
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据用户id查询用户详情", notes = "我是备注说明", response = Message.class)
	@ApiResponses(value = { @ApiResponse(code = UserServiceException.USERINFO_NOT_EXIST, message = "未找到相应用户信息！", response = UserServiceException.class)})
	@ApiImplicitParam(value = "用户id", name = "id", paramType = "path", required = true, dataType = "long")
	
	@ControllerLog(content = "查询用户详情", operationType = OperateLogTypeEnum.QUERYA)
	@Permission("user:detail")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Message getDetail(@PathVariable("id") long id) {
		Message message = new Message();
		User user = this.userService.getUserById(id);
		message.setData(user);
		return message;
		
	}
	
	/**
	 * 获取所有用户列表
	 * @return
	 */
	@ControllerLog(content = "查询所用用户列表", operationType = OperateLogTypeEnum.QUERYA)
	@Permission("user:list")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public Message listAllUsers() {
		Message message = new Message();
        List<User> users = this.userService.getAllUser();
        message.setMessage("获取列表成功！");
        message.setData(users);
        return message;    }
	
	/**
	 * 分页获取用户列表
	 * @param param
	 * @return
	 */
	@Permission("user:list")
	@RequestMapping(value = "/listUser", method = RequestMethod.POST)
	public Message listUserPage(@RequestBody Parameter<User> param) {
		Message message = new Message();
		//super.setDataPermission(param.getParamMap());	// 设置数据权限
		Page page = this.userService.getUserPage(param);
		message.setMessage("获取列表成功！");
        message.setData(page.getRecordList());
        message.setTotalCount(page.getTotalCount());
        return message;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @param ucBuilder
	 * @return
	 */
	@ControllerLog(content = "添加用户", operationType = OperateLogTypeEnum.ADD)
	@Permission("user:create")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Message createUser(@Valid @RequestBody User user/*, BindingResult result*/) {
		Message message = new Message();
		this.userService.addUser(user);
		//this.logSave("添加用户成功！");
		message.setMessage("添加用户成功！");
		message.setSuc();
		return message;
	}
	
	/**
	 * 更新用户信息
	 * @Valid验证前台传过来的参数是否合法
	 * @param id
	 * @param user
	 * @return
	 */
	@ControllerLog(content = "更新用户", operationType = OperateLogTypeEnum.UPDATE)
	@Permission("user:update")
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Message updateUser(@Valid @RequestBody User user, BindingResult result) {
		Message message = new Message();
		// 相关字段是否验证失败
		if(result.hasErrors()) {
			message.setValidFail(super.loadFieldError(result));
		} else {
			this.userService.updateUser(user);
			message.setMessage("更新成功！");
			message.setSuc();
		}
        return message;
    }
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@ControllerLog(content = "删除用户", operationType = OperateLogTypeEnum.DELETE)
	@Permission("user:delete")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Message deleteUser(@PathVariable("id") long id) {
		Message message = new Message();
		this.userService.deleteUser(id);
    	message.setSuc();
        return message;
    }
	
	/**
	 * 激活、锁定用户
	 * @param id
	 * @param status
	 * @return
	 */
	@Permission("user:update")
	@RequestMapping(value = "/user/{id}/lock/{status}", method = RequestMethod.GET)
	public Message lock(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
		Message message = new Message();
		this.userService.updateUserStatus(id, status);
		message.setSuc();
		return message;
	}
	
	/**
	 * 测试@CurrentUser注解
	 * 从缓存中取出当前登陆用户的信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/insertSomthing", method = RequestMethod.GET)
	public Message insertSomthing(@CurrentUser User user) {
		Message message = new Message();
		message.setSuc();
		message.setData(user);
		return message;
	}
	
}