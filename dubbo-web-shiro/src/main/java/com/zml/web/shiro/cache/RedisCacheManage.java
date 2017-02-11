package com.zml.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.common.utils.cache.redis.RedisUtil;

public class RedisCacheManage implements CacheManager {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheManage.class);  
	
	@Autowired
	private RedisUtil<Session> redisUtil;

	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.info("获取名称为: " + name + " 的RedisCache实例!");  
		Cache c = this.getCache(name);
		
		return null;
	}

}
