package com.zml.user.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
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

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private RedisUtil<User> redisUtil;
	
    @Autowired
    protected PasswordHelper passwordHelper;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addUser(User user) throws UserServiceException {
		// 测试回滚
		if("666".equals(user.getPasswd())) {
			throw new UserServiceException(UserServiceException.USERINFO_IS_EXIST, "用户已经存在！！");
		}
		/*Long l = 0L;
		try {
			l = this.userDao.insert(user);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.USERINFO_IS_EXIST, "添加用户失败！！");
		}
		return l;*/
		
		//加密密码
        this.passwordHelper.encryptPassword(user);
		return this.userDao.insert(user);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void updateUser(User user) throws UserServiceException {
		this.userDao.update(user);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void deleteUser(Long id) throws UserServiceException {
		this.userDao.deleteById(id);
		
	}
	
	public User getUserByName(String userName) throws UserServiceException {
		return this.userDao.getUserByName(userName);
	}

	public User getUserByStaffId(String staffNum) throws UserServiceException {
		return this.userDao.getUserByStaffNum(staffNum);
	}

	public User getUserById(Long id) throws UserServiceException {
		return this.userDao.getById(id);
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
		this.userDao.updateUserStatus(id, status);
	}

	@Override
	public Page getUserPage(Parameter<User> param) throws UserServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.userDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}

}
