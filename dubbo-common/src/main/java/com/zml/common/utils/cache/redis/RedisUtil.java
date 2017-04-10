package com.zml.common.utils.cache.redis;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil<T> {
	
	private static final long DEFAULT_CACHE_SECONDS = 3600 * 2;	// 单位秒，默认2小时

    @Autowired 
    @Qualifier("redisTemplate")
    private RedisTemplate<String, T> redisTemplate;
    
    @Autowired
    @Qualifier("redisTemplate")
    protected RedisTemplate<Serializable, Serializable> redisTemplateSerializable;
    
    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key    缓存的键值
     * @param value    缓存的值
     * @return        缓存的对象
     */
    public void setCacheObject(String key, T value) {
        this.setCacheObject(key, value, DEFAULT_CACHE_SECONDS);
    }
    
    /**
     * 
     * @param key
     * @param value
     * @param seconds	缓存时间，单位秒
     */
    public void setCacheObject(String key, T value, long seconds) {
        redisTemplate.opsForValue().set(key,value);
        this.expire(key, seconds, TimeUnit.SECONDS);
    }
    
    
    /**
     * 获得缓存的基本对象。
     * @param key        缓存键值
     * @param operation
     * @return            缓存键值对应的数据
     */
    public T getCacheObject(String key) {
        return redisTemplate.opsForValue().get(key); 
    }
    
    /**
     * 缓存List数据
     * @param key        缓存的键值
     * @param dataList    待缓存的List数据
     * @return            缓存的对象
     */
    public ListOperations<String, T>  setCacheList(String key, List<T> dataList) {
        return this.setCacheList(key, dataList, DEFAULT_CACHE_SECONDS);
    }
    
    /**
     * 缓存List数据, 自定义时间
     * @param key
     * @param dataList
     * @param seconds
     * @return
     */
    public ListOperations<String, T>  setCacheList(String key, List<T> dataList, long seconds) {
    	ListOperations<String, T> listOperation = redisTemplate.opsForList();
    	if(null != dataList)
    	{
    		int size = dataList.size();
    		for(int i = 0; i < size ; i ++)
    		{
    			listOperation.rightPush(key,dataList.get(i));
    		}
    	}
    	this.expire(key, seconds, TimeUnit.SECONDS);
    	return listOperation;
    }
    
    /**
     * 移出并获取列表的第一个元素
     * @param key    缓存的键值
     * @return       缓存键值对应的数据
     */
    public List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        for(int i = 0 ; i < size ; i ++)
        {
            dataList.add(listOperation.leftPop(key));
        }
        return dataList;
    }
    
    /**
     * 获取list指定范围内的元素
     * @Title: lrange 
     * @param @param key
     * @param @param start
     * @param @param end
     * @param @return    
     * @return List<T>    返回类型 
     * @throws
     */
    public List<T> lrange(String key, long start, long end) {
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        return listOperation.range(key, start, end);
    }
    
    /** 
     * list集合长度
     * @param key 
     * @return 
     */  
    public Long listSize(String key) {  
        return redisTemplate.opsForList().size(key);  
    }  
    
    /**
     * 覆盖操作,将覆盖List中指定位置的值
     * @param key
     * @param int index 位置
     * @param String
     *            value 值
     * @return 状态码
     * */
    public void listSet(String key, int index, T obj) {
        redisTemplate.opsForList().set(key, index, obj);
    }
        
    /**
     * 向List尾部追加记录
     * 
     * @param String
     *            key
     * @param String
     *            value
     * @return 记录总数
     * */
    public long leftPush(String key, T obj) {
        return redisTemplate.opsForList().leftPush(key, obj);
    }

    /**
     * 向List头部追加记录
     * 
     * @param String
     *            key
     * @param String
     *            value
     * @return 记录总数
     * */
    public long rightPush(String key, T obj) {
        return redisTemplate.opsForList().rightPush(key, obj);
    }
    
    /**
     * 算是删除吧，只保留start与end之间的记录
     * 
     * @param String
     *            key
     * @param int start 记录的开始位置(0表示第一条记录)
     * @param int end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     * */
    public void trim(String key, int start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
    
    /**
     * 删除List中c条记录，被删除的记录值为value
     * 
     * @param String
     *            key
     * @param int c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param Object
     *            obj 要匹配的值
     * @return 删除后的List中的记录数
     * */
    public long remove(String key, long i, T obj) {
        return redisTemplate.opsForList().remove(key, i, obj);
    }
    
    /**
     * 缓存Set
     * @param key        缓存键值
     * @param dataSet    缓存的数据
     * @return            缓存数据的对象
     */
	public BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        return this.setCacheSet(key, dataSet, DEFAULT_CACHE_SECONDS);
    }
	
	/**
	 * 缓存Set，设置过期时间
	 * @param key
	 * @param dataSet
	 * @param seconds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet, long seconds) {
		BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);    
		Iterator<T> it = dataSet.iterator();
		while(it.hasNext())
		{
			setOperation.add(it.next());
		}
		this.expire(key, seconds, TimeUnit.SECONDS);
		return setOperation;
	}
    
    /**
     * 获得缓存的set
     * @param key
     * @param operation
     * @return
     */
    public Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String,T> operation = redisTemplate.boundSetOps(key);    
        
        Long size = operation.size();
        for(int i = 0 ; i < size ; i++)
        {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }
    
    /**
     * 缓存Map
     * @param key
     * @param dataMap
     * @return
     */
    public int setCacheMap(String key,Map<String, Object> dataMap) {
        return this.setCacheMap(key, dataMap, DEFAULT_CACHE_SECONDS);
    }
    
    public int setCacheMap(String key,Map<String, Object> dataMap, long seconds) {
    	if(null != dataMap) {
    		HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
    		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {  
    			if(hashOperations != null) {
    				hashOperations.put(key,entry.getKey(),entry.getValue());
    			} else {
    				return 0;
    			}
    		} 
    		if(hashOperations.size(key) > 0) {
    			this.expire(key, seconds, TimeUnit.SECONDS);
    		}
    	} else {
    		return 0;
    	}
    	return dataMap.size();
    }
    
    /**
     * 获得缓存的Map
     * @param key
     * @param hashOperation
     * @return
     */
    public Map<Object, Object> getCacheMap(String key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }
    
    /**
     * 缓存Map
     * @param key
     * @param dataMap
     * @return
     */
    public void setCacheIntegerMap(String key,Map<Integer, Object> dataMap) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        if(null != dataMap)
        {
            for (Map.Entry<Integer, Object> entry : dataMap.entrySet()) {  
                hashOperations.put(key,entry.getKey(),entry.getValue());
            } 
            
        }
    }
    
    /**
     * 获得缓存的Map
     * @param key
     * @param hashOperation
     * @return
     */
    public Map<Object, Object> getCacheIntegerMap(String key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }
    
    /**
     * 从hash中删除指定的存储
     * 
     * @param String
     * @return 状态码，1成功，0失败
     * */
    public long deleteMap(String key) {
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate.opsForHash().delete(key);
    }
    
     /**
      * 设置过期时间
      * @param key
      * @param time
      * @param unit
      * @return
      */
    public boolean expire(String key, long time, TimeUnit unit) {
        return redisTemplate.expire(key, time, unit);
    }
    
    /**
     * 移除给定 key 的过期时间，使得 key 永不过期
     * @param key
     * @return
     */
    public boolean persist(String key) {
    	return redisTemplate.persist(key);
    }
    
    /**
     * increment
     * @param key
     * @param step
     * @return
     */
    public long increment(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }
    
    /**
     * 根据key删除
     * @param key
     */
    public void delete(String key) {
    	redisTemplate.delete(key);
    }
    
    /**
     * 模糊删除前缀key的值(数据量大的时候慎用)
     * @param key
     */
    public void deleteByPrex(String key) {
    	redisTemplate.delete(keys(key));
    }
    
    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean isExistKey(String key) {
    	return redisTemplate.hasKey(key);
    }
    
    /**
     * 模糊匹配keys,KEYS * 命令，当数据规模较大时使用，会严重影响Redis性能 慎用
     * @param keysPattern
     * @return
     */
    public Set<String> keys(String keysPattern) {
    	return redisTemplate.keys(keysPattern+"*");
    }
    
    /**
     * 根据key获取过期时间 (TTL key)
     * @param key
     * @param timeUnit
     * @return
     */
    public long getExpire(String key, TimeUnit... timeUnit) {
    	if(timeUnit.length > 0) {
    		return redisTemplate.getExpire(key, timeUnit[0]);
    	} else {
    		return redisTemplate.getExpire(key);
    	}
    }
    /**
     * 删除redis的所有数据
     */
    /*@SuppressWarnings({"unchecked", "rawtypes"})
    public String flushDB() {
        return redisTemplateSerializable.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }*/
    
    public Long del(final byte[] key) {  
        return (Long) redisTemplateSerializable.execute(new RedisCallback<Object>() {  
            public Long doInRedis(RedisConnection connection) {  
                return connection.del(key);  
            }  
        });  
    }  

    @SuppressWarnings({"unchecked", "rawtypes"})
    public byte[] get(final byte[] key) {
        return (byte[]) redisTemplateSerializable.execute(new RedisCallback() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key);
            }
        });
    }
    
    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplateSerializable.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

}