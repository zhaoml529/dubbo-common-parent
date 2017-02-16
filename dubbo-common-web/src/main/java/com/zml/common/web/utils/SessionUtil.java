package com.zml.common.web.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.zml.common.constant.SystemConstant;
import com.zml.user.entity.User;


public class SessionUtil {

	/**
     * 设置用户到session
     * @param session
     * @param user
     */
    public static void saveUserToSession(User user) {
    	SecurityUtils.getSubject().getSession().setAttribute(SystemConstant.CURRENT_USER, user);
    }

    /**
     * 从Shiro 的Session获取当前用户信息
     * @param session
     * @return
     */
    public static User getUserFromSession() {
    	Subject currentUser = SecurityUtils.getSubject();
    	User user = (User) currentUser.getSession().getAttribute(SystemConstant.CURRENT_USER);
    	return user;
    }

    /**
     * 从Session移除当前用户信息
     * @param session
     */
    public static void removeUserFromSession(Session session) {
    	session.removeAttribute(SystemConstant.CURRENT_USER);
    }
    
    /**
     * 获取ip地址
     * @return
     */
    public static String getIpAddr() {
    	return SecurityUtils.getSubject().getSession().getHost();
    }
}
