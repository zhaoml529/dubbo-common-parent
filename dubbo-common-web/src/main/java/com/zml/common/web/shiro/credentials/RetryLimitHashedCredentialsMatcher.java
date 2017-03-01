package com.zml.common.web.shiro.credentials;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.utils.SessionUtil;
import com.zml.user.entity.User;
import com.zml.user.service.IUserService;


/**
 * 输错5次密码锁定2分钟
 * @author ZML
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;
    
    private Cache<String, AtomicBoolean> jcaptchaCache;
    
    @Autowired
    private RedisUtil<Object> redisUtil;
    
    @Autowired
	private IUserService userService;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
        jcaptchaCache = cacheManager.getCache("jcaptchaCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	
        String userName = (String)token.getPrincipal();
        // retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(this.getPwdRetryKey(userName));
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(this.getPwdRetryKey(userName), retryCount);
        }
        //密码输错3次，显示验证码
        if(retryCount.incrementAndGet() == SystemConstant.PASSWORD_SHOW_JCAPTCHA ) {
        	AtomicBoolean enabled = jcaptchaCache.get(this.getJcaptcharKey(userName));
        	if(enabled == null) {
        		enabled = new AtomicBoolean(true);
        		jcaptchaCache.put(this.getJcaptcharKey(userName), enabled);
        	}
        }
        //多于5次锁定2分钟
        if(retryCount.get() > SystemConstant.PASSWORD_RETRY_COUNT) {
            // if retry count > 5 throw
        	if(this.redisUtil.getExpire(this.getPwdRetryKey(userName), TimeUnit.SECONDS) <= 0) {
        		this.redisUtil.expire(this.getPwdRetryKey(userName), 120, TimeUnit.SECONDS);	// 2分钟过期
        	}
            throw new ExcessiveAttemptsException();
        }

        String uName = (String)token.getPrincipal();
        User user = this.userService.getUserByName(uName);
        
        //匹配用户输入的token的凭证（未加密）与系统提供的凭证（已加密）  
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            // clear redis cache
            passwordRetryCache.remove(this.getPwdRetryKey(userName));
            jcaptchaCache.remove(this.getJcaptcharKey(userName));
            
            // update login log
            SessionUtil.saveUserToSession(user);
            user.setLastLoginTime(new Date());
        } else {
        	user.setPwdErrorLastTime(new Date());
        }
        this.userService.updateUser(user);
        return matches;
    }
    
	private String getPwdRetryKey(String key) {  
		return CacheConstant.PWD_RETRY_COUNT + key;
    }
    
	private String getJcaptcharKey(String key) {  
		return CacheConstant.JCAPTCHA_ENABLED + key;
    }
}
