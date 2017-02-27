package com.zml.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zml.user.entity.User;

@Component
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName;		//指定散列算法为MD5,还有别的算法如：SHA256、SHA1、SHA512
    @Value("${password.hashIterations}")
    private int hashIterations;		//散列迭代次数 md5(md5(pwd)): new Md5Hash(pwd, salt, 2).toString()

    //加密：输入明文得到密文
    public void encryptPassword(User user) {

        String salt = user.getSalt();
        if(StringUtils.isBlank(salt)) {
        	salt = randomNumberGenerator.nextBytes().toHex();
        	user.setSalt(salt);
        }
        
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPasswd(),
                ByteSource.Util.bytes(salt),	//@see User getCredentialsSalt():name+salt -> 不用name+salt了(修改用户名时有问题)，直接使用盐。
                hashIterations).toHex();

        user.setPasswd(newPassword);
    }
}
