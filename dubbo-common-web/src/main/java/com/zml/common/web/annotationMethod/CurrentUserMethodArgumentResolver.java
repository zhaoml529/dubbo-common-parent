package com.zml.common.web.annotationMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.zml.common.constant.CacheConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.annotation.CurrentUser;
import com.zml.user.entity.User;
import com.zml.user.service.IUserService;

/**
 * 将含有CurrentUser注解的方法参数注入当前登录用户
 * @author zhao
 *
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisUtil<User> redisUtil;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是User并且有CurrentUser注解则支持
		if (parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
	}
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
		Long userId = (Long) webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
		if(userId != null) {
			User user = this.redisUtil.getCacheObject(CacheConstant.CURRENT_USER_ID + userId.toString());
			if(user == null) {
				user = this.userService.getUserById(userId);
				this.redisUtil.setCacheObject(CacheConstant.CURRENT_USER_ID + userId.toString(), user);	// 默认过期时间2小时,token过期考虑清除缓存
			}
			return user;
		}
		throw new MissingServletRequestPartException(currentUserAnnotation.value()); 
	}


}
