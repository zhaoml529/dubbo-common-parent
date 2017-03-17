package com.zml.common.web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zml.user.entity.User;

public class TokenUtil {

	private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
	
	public static String getTokenString(User user, Date expires, Key key, Map<String,Object> claims) throws Exception {
		if(user == null) {
			throw new NullPointerException("TokenUtil:null username is illegal");
		}
		if(expires == null) {
			throw new NullPointerException("TokenUtil:null expires is illegal");
		}
		if(key == null) {
			throw new NullPointerException("TokenUtil:null key is illegal");
		}
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		String token = Jwts.builder()
					.setIssuer("dili-group")			// 设置发行人
					.setSubject(user.getUserName())		// 设置抽象主题
					.setAudience("user")				// 设置角色
					.setExpiration(expires)				// 设置过期时间
					.setClaims(claims)					
					.setIssuedAt(new Date())			// 设置现在时间
					.setId(user.getId().toString())		// 设置版本号
					.signWith(signatureAlgorithm, key)
					.compact();
		
		return token;
	}
	
	public static boolean isValid(String token, Key key) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
        	logger.error("token验证失败", e);
            return false;
        }
    }
	
	public static String getName(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            String name = String.valueOf(claimsJws.getBody().get("name"));
            return name;
        }
        return null;
    }
	
	public static String[] getRoles(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getAudience().split(",");
        }
        return new String[]{};
    }
}
