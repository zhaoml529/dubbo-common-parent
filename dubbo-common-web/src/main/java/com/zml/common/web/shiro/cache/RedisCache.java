package com.zml.common.web.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zml.common.constant.CacheConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.common.utils.cache.redis.SerializeUtils;



public class RedisCache<K,V> implements Cache<K,V> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());  
	private RedisUtil<V> redisUtil;
	
	public RedisCache(RedisUtil<V> redisUtil) {
		this.redisUtil = redisUtil;
	}

	public V get(K key) throws CacheException {
		logger.debug("根据key获取,key=" + key + " RedisCacheKey: " + this.getRedisCacheKey(key));  
		V value = this.redisUtil.getCacheObject(this.getRedisCacheKey(key));
		return value;
	}

	public V put(K key, V value) throws CacheException {
		logger.info("根据key存储value,key=" + key + " RedisCacheKey: " + this.getRedisCacheKey(key) + "value: " + value);  
		try {
			this.redisUtil.setCacheObject(this.getRedisCacheKey(key), value);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return value;
	}

	public V remove(K key) throws CacheException {
		logger.debug("根据key删除,key=" + key + " RedisCacheKey: " + this.getRedisCacheKey(key));  
		this.redisUtil.delete(this.getRedisCacheKey(key));
		return null;
	}

	/**
	 * 数据量大的时候慎用，线上慎用
	 */
	public void clear() throws CacheException {
		this.redisUtil.deleteByPrex(CacheConstant.SHIRO_REDIS_CACHE);
	}

	public int size() {
		Set<String> set = this.redisUtil.keys(CacheConstant.SHIRO_REDIS_CACHE);
		if(CollectionUtils.isNotEmpty(set)) {
			return set.size();
		} else {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		Set<String> set = this.redisUtil.keys(CacheConstant.SHIRO_REDIS_CACHE);
		if(CollectionUtils.isNotEmpty(set)) {
			return (Set<K>) set;
		} else {
			return Collections.emptySet(); 
		}
	}

	public Collection<V> values() {
		Set<K> keys = this.keys();
		List<V> values = new ArrayList<V>(keys.size());
		for(K key : keys) {
			V value = get(key); 
			if (value != null) {  
                values.add(value);  
            }
		}
		return Collections.unmodifiableList(values); 
	}
	
	private String getRedisCacheKey(K key) {  
		if(key instanceof String) {
			return CacheConstant.SHIRO_REDIS_CACHE + key;
		} else {
			return CacheConstant.SHIRO_REDIS_CACHE + new SerializeUtils().seriazileAsString(key);
		}
    }

}
