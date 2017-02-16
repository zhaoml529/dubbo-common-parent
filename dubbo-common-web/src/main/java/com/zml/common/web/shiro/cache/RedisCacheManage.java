package com.zml.common.web.shiro.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.common.utils.cache.redis.RedisUtil;

@SuppressWarnings("rawtypes")
public class RedisCacheManage implements CacheManager {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheManage.class);  
	
	// fast lookup by name map  
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();  
	
	@Autowired
	private RedisUtil redisUtil;

	@SuppressWarnings("unchecked")
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.info("获取名称为: " + name + " 的RedisCache实例!");  
		Cache c = caches.get(name); 
		if (c == null) {  
			c = new RedisCache<K, V>(redisUtil);
			caches.put(name, c);
		}
		return c;
	}

}
