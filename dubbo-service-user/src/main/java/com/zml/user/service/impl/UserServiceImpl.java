package com.zml.user.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.constant.CacheConstant;
import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.user.dao.IUserDao;
import com.zml.user.entity.User;
import com.zml.user.exceptions.UserServiceException;
import com.zml.user.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	private static final Integer LOCK = 101;
	private static final Integer ACTIVE = 100;

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private RedisUtil<User> redisUtil;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addUser(User user) throws UserServiceException {
		// 测试回滚
		/*if("666".equals(user.getPasswd())) {
			throw new UserServiceException(UserServiceException.USERINFO_IS_EXIST, "用户已经存在！！");
		}*/
		/*Long l = 0L;
		try {
			l = this.userDao.insert(user);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.USERINFO_IS_EXIST, "添加用户失败！！");
		}
		return l;*/
		if(isUserExist(user)) {
			throw UserServiceException.create("用户已经存在！", UserServiceException.USERINFO_IS_EXIST);
		} else {
			//加密密码
			String salt = user.getSalt();
			if(StringUtils.isBlank(salt)) {
				salt = getSalt(16);
				user.setSalt(salt);
			}
			String hexPassword = DigestUtils.sha256Hex(user.getPasswd() + salt);
			user.setPasswd(hexPassword);
			return this.userDao.insert(user);
		}
	}
	
	private String getSalt(int len){
		Random r = new Random();  
        StringBuilder sb = new StringBuilder(len);  
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));  
        int length = sb.length();  
        if (length < len) {  
            for (int i = 0; i < len - length; i++) {  
                sb.append("0");  
            }  
        }  
        String salt = sb.toString(); 
        return salt;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void updateUser(User user) throws UserServiceException {
		User u = this.getUserById(user.getId());
		if(u == null) {
			throw UserServiceException.create("所更新的用户不存在！", UserServiceException.USERINFO_NOT_EXIST);
		} else {
			//u.setUserName(user.getUserName());
			u.setStaffNum(user.getStaffNum());
			//u.setPasswd(user.getPasswd());
			this.userDao.update(u);
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void deleteUser(Long id) throws UserServiceException {
		User user = this.getUserById(id);
		if(user == null) {
			throw UserServiceException.create("所删除的用户不存在！", UserServiceException.USERINFO_NOT_EXIST);
		} else {
			this.userDao.deleteById(id);
		}
	}
	
	public User getUserByName(String userName) throws UserServiceException {
		return this.userDao.getUserByName(userName);
	}

	public User getUserByStaffId(String staffNum) throws UserServiceException {
		return this.userDao.getUserByStaffNum(staffNum);
	}

	public User getUserById(Long id) throws UserServiceException {
		User user = this.userDao.getById(id);
		if(user == null) {
			throw UserServiceException.create("未找到相应用户信息！", UserServiceException.USERINFO_NOT_EXIST);
		} else {
			return user;
		}
	}

	public boolean isUserExist(User user) throws UserServiceException {
		User u = this.getUserByName(user.getUserName());
		if(u != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<User> getAllUser() throws UserServiceException {
		List<User> userList = this.redisUtil.lrange(CacheConstant.ALL_USER_LIST, 0, -1);	// 从缓存查询userlist是否存在
		if(CollectionUtils.isEmpty(userList)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			List<User> list = this.userDao.getList(paramMap);
			if(CollectionUtils.isEmpty(list)) {
				return Collections.emptyList();
			} else {
				this.redisUtil.setCacheList(CacheConstant.ALL_USER_LIST, list);
				this.redisUtil.expire(CacheConstant.ALL_USER_LIST, 2, TimeUnit.HOURS);		// key过期时间2小时
				return list;
			}
		} else {
			return userList;
		}
	}

	@Override
	public void updateUserStatus(Long id, Integer status)
			throws UserServiceException {
		if(id == null || status == null) {
			throw UserServiceException.create("未找到相应用户信息！", UserServiceException.USERINFO_NOT_EXIST);
		} else {
			User user = this.getUserById(id);
			if (user == null) {
				throw UserServiceException.create("未找到相应用户信息！", UserServiceException.USERINFO_NOT_EXIST);
	        } else {
	        	if(status == 100) { // 锁定
	        		this.userDao.updateUserStatus(id, LOCK);
	        	} else {			// 激活
	        		this.userDao.updateUserStatus(id, ACTIVE);
	        	}
	        }
		}
	}

	@Override
	public Page getUserPage(Parameter<User> param) throws UserServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.userDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}

	@Override
	public List<String> getPermissionByUserId(String userId)
			throws UserServiceException {
		List<String> permissionList = this.userDao.getPermissionByUserId(userId);
		if(CollectionUtils.isNotEmpty(permissionList)) {
			return permissionList;
		} else {
			return Collections.emptyList();
		} 
	}

	@Override
	public List<String> getPermissionByStaffNuym(String staffNum)
			throws UserServiceException {
		List<String> permissionList = this.userDao.getPermissionByStaffNuym(staffNum);
		if(CollectionUtils.isNotEmpty(permissionList)) {
			return permissionList;
		} else {
			return Collections.emptyList();
		}
	}
	
	public static void main(String[] args) {
		Random r = new Random();  
        StringBuilder sb = new StringBuilder(16);  
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));  
        int length = sb.length();  
        if (length < 16) {  
            for (int i = 0; i < 16 - length; i++) {  
                sb.append("0");  
            }  
        }  
        String salt = sb.toString(); 
        System.out.println("salt: "+salt);
        
        String hexPassword = DigestUtils.sha256Hex("123" + salt);
        System.out.println("passwd: "+hexPassword);
	}
}
