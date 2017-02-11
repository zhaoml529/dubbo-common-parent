package com.zml.web.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.common.constant.CacheConstant;
import com.zml.common.utils.cache.redis.RedisUtil;

public class ReidsSessionDao extends CachingSessionDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReidsSessionDao.class);  
	
	@Autowired
	private RedisUtil<Session> redisUtil;
	
	private long expire = 3600000;	// 会话过期时间1小时

	@Override
	protected void doDelete(Session session) {
		if(session == null || session.getId() == null){  
            logger.error("session or session id is null!");  
            return;  
        }  
		this.redisUtil.delete(this.getRedisSessionKey(session.getId()));
		logger.info("shiro session id {} 被删除", session.getId());
	}

	@Override
	protected void doUpdate(Session session) {
		this.saveSession(session);
		logger.info("shiro session updated!");
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);   
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		logger.info("shiro session created!");
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = this.redisUtil.getCacheObject(this.getRedisSessionKey(sessionId));
		if (session == null) {
			throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
		}
		return session;
	}
	
	//用来统计当前活动的session,如果用户特别多 redis的keys会影响性能  
    @Override  
    public Collection<Session> getActiveSessions() {  
        Set<Session> sessions = new HashSet<Session>();  
        Set<String> keys = this.redisUtil.keys(CacheConstant.SHIRO_REDIS_SESSION);
        if(CollectionUtils.isNotEmpty(keys)) {
        	for(String key : keys) {
        		Session s = this.redisUtil.getCacheObject(key);
        		sessions.add(s);
        	}
        }
        return sessions;  
    }
	
	private void saveSession(Session session) {
		if(session == null || session.getId() == null){  
			logger.error("session or session id is null");  
			throw new UnknownSessionException("session or sessionId returned from saveSession implementation is null!");
        }  
		session.setTimeout(expire);
		this.redisUtil.setCacheObject(this.getRedisSessionKey(session.getId()), session);
		this.redisUtil.expire(this.getRedisSessionKey(session.getId()), expire, TimeUnit.MILLISECONDS);
	}
	
	private String getRedisSessionKey(Serializable sessionId) {  
        return CacheConstant.SHIRO_REDIS_SESSION + sessionId.toString();  
    } 

}
