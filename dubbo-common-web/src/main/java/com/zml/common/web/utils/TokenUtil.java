package com.zml.common.web.utils;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zml.common.constant.SystemConstant;
import com.zml.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
	
	/**
	 * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
	 * @param user
	 * @param expires
	 * @param key
	 * @param claims
	 * @return
	 * @throws Exception
	 */
	public static String getTokenString(User user, long expiresSecond, String issuer, String base64Secret, Map<String,Object> claims) throws Exception {
		if(user == null) {
			throw new NullPointerException("TokenUtil:null username is illegal");
		}
		if(expiresSecond < 0) {
			throw new NullPointerException("TokenUtil:null expires is illegal");
		}
		if(StringUtils.isBlank(base64Secret)) {
			throw new NullPointerException("TokenUtil:null key is illegal");
		}
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		
		//生成签名密钥
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		
		
		JwtBuilder builder = Jwts.builder()
					.setIssuer(issuer)			// 设置发行人
					.setSubject(user.getUserName())		// 设置抽象主题
					.setAudience("user")				// 设置接收人
					.setClaims(claims)					
					.setIssuedAt(new Date())			// 设置现在时间
					.setId(user.getId().toString())		// 设置版本号
					.signWith(signatureAlgorithm, signingKey);
		
		// 设置token过期时间
		if(expiresSecond >= 0) {
			long TTLMillis = expiresSecond * 1000;			// 剩余时间(剩余有效时间)
			long nowMillis = System.currentTimeMillis();	// 现在时间
			Date now = new Date(nowMillis);			
			long expMillis = nowMillis + TTLMillis;
			Date exp = new Date(expMillis);
			
			builder.setExpiration(exp).setNotBefore(now);// 设置过期时间
		}
		
		return builder.compact();
	}
	
	public static boolean isValid(String token, String base64Secret) {
        try {
            Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret)).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
        	logger.error("token验证失败", e);
            return false;
        }
    }
	
	public static String getUserId(String token, String base64Secret) {
        if (isValid(token, base64Secret)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret)).parseClaimsJws(token);
            String name = String.valueOf(claimsJws.getBody().get(SystemConstant.CURRENT_USER_ID));
            return name;
        }
        return null;
    }
	
	public static String getUserName(String token, String base64Secret) {
		if (isValid(token, base64Secret)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret)).parseClaimsJws(token);
			String name = String.valueOf(claimsJws.getBody().get(SystemConstant.CURRENT_USER_NAME));
			return name;
		}
		return null;
	}
	
	public static String getRoles(String token, String base64Secret) {
        if (isValid(token, base64Secret)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret)).parseClaimsJws(token);
            return claimsJws.getBody().getAudience();
        }
        return null;
    }
}
