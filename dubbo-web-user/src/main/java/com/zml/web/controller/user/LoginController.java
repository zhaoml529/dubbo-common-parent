package com.zml.web.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zml.common.constant.CacheConstant;
import com.zml.common.constant.SystemConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.web.base.BaseController;
import com.zml.common.web.entity.Message;
import com.zml.common.web.utils.TokenUtil;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

@RestController
@RequestMapping("/api")
public class LoginController  extends BaseController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisUtil<Object> redisUtil;
	
	@Autowired
	private RedisUtil<String> stringRedis;
	
	@Value("${jwt.info.base64Secret}")
    private String base64Secret;		// key
	
	@Value("${jwt.info.issuer}")
	private String issuer;				// 发行者
	
	@Value("${jwt.info.expiresSecond}")
	private long expiresSecond;			// 有效期 默认2天
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message Login(@RequestParam("username") String userName, @RequestParam("password") String password) throws Exception {
		Message message = new Message();
		User user = this.userService.getUserByName(userName);
		if(user == null) {
			super.logLoginErr("用户名或密码错误！" + UserServiceException.LOGIN_LOGNAME_NOT_EXIST, new User(userName));
			throw new UserServiceException(UserServiceException.LOGIN_LOGNAME_NOT_EXIST, "用户名或密码错误！");
		}
		if(user.getStatus() == 101) {
			super.logLoginErr("账号已被锁定！" + UserServiceException.LOGIN_USER_INACTIVE, user);
			throw new UserServiceException(UserServiceException.LOGIN_USER_INACTIVE, "账号已被锁定！");
		}
		// 加密明文密码，验证密码
		if(user.getPasswd().equals(DigestUtils.sha256Hex(password + user.getSalt()))) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put(SystemConstant.CURRENT_USER_ID, user.getId());
			claims.put(SystemConstant.CURRENT_USER_NAME, user.getUserName());
			
			String token = TokenUtil.getTokenString(user, this.expiresSecond, this.issuer, this.base64Secret, claims);
			// 缓存用户信息
			this.redisUtil.setCacheObject(CacheConstant.CURRENT_USER_ID + user.getId().toString(), user);	// 默认过期时间2小时,token过期考虑清除缓存
			// 缓存权限信息
			List<String> permissions = this.cachePermissions(user.getId().toString());
			
			// 返回数据到前台
			Map<String, Object> data = new HashMap<String, Object> ();
			data.put("token", token);
			data.put("permissions", permissions);
			
			message.setSuc();
			message.setData(data);
			super.logLogin("登录系统！", user);
		} else {
			super.logLoginErr("用户名或密码错误！" + UserServiceException.LOGIN_PWD_ERROR, user);
			throw new UserServiceException(UserServiceException.LOGIN_PWD_ERROR, "用户名或密码错误！");
		}
		return message;
    }
	
	/**
	 * 缓存用户权限列表
	 * @param userId
	 */
	private List<String> cachePermissions(String userId) {
		// 从缓存取出权限字符串
		List<String> permissions = this.stringRedis.lrange(CacheConstant.USER_PERMISSION_KEY + userId, 0, -1);
		if(permissions.isEmpty()) {
			// 根据用户编制号获取权限字符串列表
			permissions = this.userService.getPermissionByUserId(userId);
			// 将权限列表放入缓存
			this.stringRedis.setCacheList(CacheConstant.USER_PERMISSION_KEY + userId, permissions);
		}
		return permissions;
	}
	
}
