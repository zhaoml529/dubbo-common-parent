package com.zml.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.entity.Message;
import com.zml.common.web.utils.TokenUtil;
import com.zml.log.annotation.ControllerLog;
import com.zml.log.enums.OperateLogTypeEnum;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisUtil<User> redisUtil;
	
	@Value("${jwt.info.base64Secret}")
    private String base64Secret;		// key
	
	@Value("${jwt.info.issuer}")
	private String issuer;				// 发行者
	
	@Value("${jwt.info.expiresSecond}")
	private long expiresSecond;			// 有效期
	
	@ControllerLog(content = "登录系统", operationType = OperateLogTypeEnum.LOGIN)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
    public Message showLoginForm(@RequestParam("username") String userName, @RequestParam("password") String password) throws Exception {
		Message message = new Message();
		User user = this.userService.getUserByName(userName);
		if(user == null) {
			throw new UserServiceException(UserServiceException.LOGIN_LOGNAME_NOT_EXIST, "用户名或密码错误！");
		}
		if(user.getStatus() == 101) {
			throw new UserServiceException(UserServiceException.LOGIN_USER_INACTIVE, "账号已被锁定！");
		}
		// 加密明文密码，验证密码
		if(user.getPasswd().equals(DigestUtils.sha256Hex(password + user.getSalt()))) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put(SystemConstant.CURRENT_USER_ID, user.getId());
			claims.put(SystemConstant.CURRENT_USER_NAME, user.getUserName());
			
			//Date expires = getExpiryDate(30 * 24 * 60);// 30天的有效日期
			String token = TokenUtil.getTokenString(user, this.expiresSecond, this.issuer, this.base64Secret, claims);
			this.redisUtil.setCacheObject(CacheConstant.CURRENT_USER_ID + user.getId().toString(), user);	// 默认过期时间2小时,token过期考虑清除缓存
			System.out.println("token:" + token);
			message.setSuc();
			message.setData(token);
		} else {
			throw new UserServiceException(UserServiceException.LOGIN_PWD_ERROR, "用户名或密码错误！");
		}
		return message;
    }
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
}
