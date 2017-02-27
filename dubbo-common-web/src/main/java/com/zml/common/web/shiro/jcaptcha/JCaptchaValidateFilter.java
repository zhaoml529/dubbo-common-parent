package com.zml.common.web.shiro.jcaptcha;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.zml.common.constant.CacheConstant;


/**
 * 用于验证码验证的Shiro 过滤器
 * @author zml
 *
 */
public class JCaptchaValidateFilter extends AccessControlFilter {

    // private boolean jcaptchaEnabled = true;//是否开启验证码支持

    private String jcaptchaParam = "jcaptchaCode";//前台提交的验证码参数名

    private String failureKeyAttribute = "shiroLoginFailure"; //验证码验证失败后存储到的属性名
    
    private Cache<String, AtomicBoolean> jcaptchaCache;
    
    public void setCacheManager(CacheManager cacheManager) {
        this.jcaptchaCache = cacheManager.getCache("jcaptchaCache");
    } 

	public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }
    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
    	String userName = request.getParameter("userName");
    	AtomicBoolean enabled = jcaptchaCache.get("jcaptchaEnabled");
    	if(enabled == null){
    		enabled = new AtomicBoolean(false);
    	}
        request.setAttribute("jcaptcha", enabled.get());

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (!"post".equalsIgnoreCase(httpServletRequest.getMethod()) || enabled.get() == false) {
            return true;
        } 
        //3、此时是表单提交，验证验证码是否正确
        return JCaptcha.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //如果验证码失败了，存储失败key属性
        request.setAttribute(failureKeyAttribute, "jCaptcha.error");
        return true;
    }
    
	private String getJcaptcharKey(String key) {  
		return CacheConstant.JCAPTCHA_ENABLED + key;
    }
}
