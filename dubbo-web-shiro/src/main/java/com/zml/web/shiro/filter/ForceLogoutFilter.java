package com.zml.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.zml.common.constant.SystemConstant;

/**
 * 强制用户下线
 * isAccessAllowed：表示是否允许访问,mappedValue就是[urls]配置中拦截器参数部分,如果允许访问返回true，否则false;
 * onAccessDenied：表示当访问拒绝时是否已经处理了,如果返回true表示需要继续处理;如果返回false表示该拦截器实例已经处理了,直接返回即可.
 * @author zml
 *
 */
public class ForceLogoutFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	// 如果没登录就不做操作
        Session session = getSubject(request, response).getSession(false);
        if(session == null) {
            return true;
        }
        return session.getAttribute(SystemConstant.SESSION_FORCE_LOGOUT_KEY) == null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            getSubject(request, response).logout();//强制退出
        } catch (Exception e) {/*ignore exception*/}

        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}
