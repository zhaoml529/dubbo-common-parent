package com.zml.common.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.annotation.Permission;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;
/**
 * 权限拦截器
 * 拦截标有@Permission的controller方法
 * @author zhaomingliang
 * @date 2017年3月27日
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private  static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor. class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisUtil<String> stringRedis;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod methodHandler=(HandlerMethod) handler;
			Permission permission = methodHandler.getMethodAnnotation(Permission.class);
			if(permission != null && StringUtils.isNotBlank(permission.value())) {
				final String userId = (String) request.getAttribute(SystemConstant.CURRENT_USER_ID);
				if(StringUtils.isNotBlank(userId)) {
					// 从缓存取出权限字符串
					List<String> permissions = this.stringRedis.lrange(CacheConstant.USER_PERMISSION_KEY + userId, 0, -1);
					if(permissions.isEmpty()) {
						// 根据用户编制号获取权限字符串列表
						permissions = this.userService.getPermissionByUserId(userId);
						// 将权限列表放入缓存
						this.stringRedis.setCacheList(CacheConstant.USER_PERMISSION_KEY + userId, permissions);
					}
					logger.info("用户权限列表userId: "+userId+"permissions: "+permissions.toString());
					// 检查权限
					if(permissions.contains(permission.value())) {
						return true;	// 拥有权限
					} else {
						response.sendError(HttpStatus.FORBIDDEN.value(), "没有权限！");
						return false;	// 没有权限
					}
				} else {
					response.sendError(UserServiceException.LOGIN_FIRST, "请先登录！");
				}
			} else {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}
}
