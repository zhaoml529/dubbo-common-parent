package com.zml.common.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zml.common.constant.SystemConstant;
import com.zml.common.web.utils.TokenUtil;

/**
 * token验证拦截器，耦合度高，需要控制哪些uri是否需要拦截，不够优雅。
 * @author zhao
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Value("${jwt.info.base64Secret}")
    private String base64Secret;		// key
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getContextPath();
		String uri = request.getRequestURI();
		String url = path + uri;
		String requestMethod = request.getMethod();	// post get options...
		
		// options是跨域复杂请求时的预检请求(Preflighted requests)的一次嗅探
		if("options".equals(requestMethod.toLowerCase())) {
			return true;
		}
		// 不需要拦截的路径
		if("/login".startsWith(url) || "/regist".startsWith(url)) {
			return true;
		}
		
		String token = request.getHeader("Authorization");	// 获取header
		System.out.println("AuthorizationInterceptor token info:"+ token);
		
		if(StringUtils.isBlank(token)) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "认证失败!");
			return false;
		}
		// Key key = WebConstant.KEY;
		if(TokenUtil.isValid(token, base64Secret)) {
			String userId = TokenUtil.getUserId(token, base64Secret);	// 解析出userId
			//String name = TokenUtil.getUserName(token,base64Secret);	// 解析出userName
			//String role = TokenUtil.getRoles(token, base64Secret);	// 解析出role
			request.setAttribute(SystemConstant.CURRENT_USER_ID, userId);			// 将token对应的userId存入request中，便于后续取出使用。
			return true;
		}  else {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "认证失败!");
			return false;
		}
	}
}
