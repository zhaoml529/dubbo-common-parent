package com.zml.web.controller.user;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zml.common.constant.CacheConstant;
import com.zml.common.utils.SpringContextUtil;

@Controller
public class LoginController {
	
	private Cache<String, AtomicBoolean> jcaptchaCache;
	
	private CacheManager cacheManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginForm(HttpServletRequest request, Model model) throws ServletException, IOException {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在！";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误！";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
        	error = "登录失败次数过多，请稍后再试！";
        } else if(AuthenticationException.class.getName().equals(exceptionClassName)) {
        	error = "身份验证失败！";
        } else if("jCaptcha.error".equals(exceptionClassName)) {
        	error = "验证码错误！";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        if(StringUtils.isNotBlank(error)) {
        	model.addAttribute("msg", error);
        }
        
        String userName = request.getParameter("userName");
        if(StringUtils.isNotBlank(userName)) {
        	this.cacheManager = SpringContextUtil.getBean("cacheManager");
        	this.jcaptchaCache = this.cacheManager.getCache("jcaptchaCache");
        	//前台是否显示验证码
        	AtomicBoolean enabled = this.jcaptchaCache.get(CacheConstant.JCAPTCHA_ENABLED + userName);
        	if(enabled != null){
        		request.setAttribute("jcaptcha", enabled.get());
        	}
        }
        
        if(request.getParameter("kickout") != null){
        	return "error/kickout";
        }
        if(request.getParameter("kickoutMsg") != null){
        	model.addAttribute("msg", "您的帐号在另一个地点登录，您已被踢出！");
        }
        if(request.getParameter("forceLogout") != null) {
        	return "error/forceLogout";
        }
        if(request.getParameter("forceLogoutMsg") != null) {
        	model.addAttribute("msg", "您已经被管理员强制退出，请重新登录！");
        }
        
        if(SecurityUtils.getSubject().isAuthenticated()) {
        	return "index";
        } else {
        	return "login";
        }
    }
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
}
