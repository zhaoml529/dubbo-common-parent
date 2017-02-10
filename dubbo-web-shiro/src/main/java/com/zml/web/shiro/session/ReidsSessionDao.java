package com.zml.web.shiro.session;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import com.zml.common.utils.cache.redis.RedisUtil;

public class ReidsSessionDao extends CachingSessionDAO {
	
	private RedisUtil<Session> redisUtil;

	@Override
	protected void doDelete(Session session) {
		
	}

	@Override
	protected void doUpdate(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);    
		this.assignSessionId(session, sessionId);
		Long expireTime = session.getTimeout() / 1000;
		this.redisUtil.setCacheObject(sessionId.toString(), session);
		return null;
	}

	@Override
	protected Session doReadSession(Serializable session) {
		// TODO Auto-generated method stub
		return null;
	}

}
