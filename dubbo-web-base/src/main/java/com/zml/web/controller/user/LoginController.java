package com.zml.web.controller.user;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zml.common.web.entity.Message;
import com.zml.common.web.utils.TokenUtil;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

@Controller
public class LoginController {
	
	public static final Key key =MacProvider.generateKey();
	
	@Autowired
	private IUserService userService;
	
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
		if(user.getPasswd().equals(DigestUtils.sha256Hex(password))) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("userName", user.getUserName());
			claims.put("userId", user.getId());
			
			Date expires = getExpiryDate(30 * 24 * 60);// 30天的有效日期
			String token = TokenUtil.getTokenString(user, expires, key, claims);
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
	
    private Date getExpiryDate(int minutes) {

    	// 根据当前日期，来得到到期日期
    	Calendar calendar = Calendar.getInstance();

    	calendar.setTime(new Date());
    	calendar.add(Calendar.MINUTE, minutes);

    	return calendar.getTime();
	}
    
}
