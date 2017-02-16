package com.zml.common.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.user.entity.Resource;
import com.zml.user.entity.Role;
import com.zml.user.entity.User;
import com.zml.user.service.IRoleAndResourceService;
import com.zml.user.service.IRoleService;
import com.zml.user.service.IUserService;

/**
 * Shiro从从Realm获取安全数据 （如用户、 角色、 权限）
 * 可以把UserRealm看为安全数据源
 * @author ZML
 *
 */
public class UserRealm extends AuthorizingRealm{
	private static final Logger logger = Logger.getLogger(UserRealm.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;

    @Autowired
    private IRoleAndResourceService rarService;
    
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String)principals.getPrimaryPrincipal();
        //Authorization 授权，即权限验证，验证某个已认证的用户是否拥有某个权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		try {
			User user = this.userService.getUserByName(userName);
	        List<Role> roleList = this.roleService.findRoleByUserId(user.getId());
	        Set<String> roles = new HashSet<String>();
	        Set<String> resources = new HashSet<String>();
	        for(Role role : roleList) {
	        	roles.add(role.getRoleType());
	        	// 根据roleId获取资源信息
	        	List<Resource> resourceList = this.rarService.getResourceByRoleId(role.getId());
	        	for(Resource resource : resourceList){
	        		resources.add(resource.getPermission());	//添加权限字符串
	        	}
	        }
	        authorizationInfo.setRoles(roles);					//角色授权
	        authorizationInfo.setStringPermissions(resources);	//权限授权
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("realm 错误！");
		}
		return authorizationInfo;
    }

    /**
     * 认证
     * 该方法主要执行以下操作:
     * 1、检查提交的进行认证的令牌信息(token)
     * 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
     * 3、对用户信息进行匹配验证。
     * 4、验证通过将返回一个封装了用户信息的 AuthenticationInfo 实例。
     * 5、验证失败则抛出 AuthenticationException 异常信息。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String)token.getPrincipal();
        User user = null;
		try {
			user = this.userService.getUserByName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(user.getIsLock() == 101) {
            throw new LockedAccountException(); //帐号锁定
        }
        //Authenticator的职责是验证用户帐号，是Shiro API中身份验证核心的入口点
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        //CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPasswd(), 	//密码
                ByteSource.Util.bytes(user.getSalt()),	//原来使用(salt+name) 修改用户名时salt就不对了，所以只用salt了。
                getName()  			//realm name
        );
        /*Session currentSession = SecurityUtils.getSubject().getSession();
        UserUtil.saveUserToSession(currentSession, user);*/
        return authenticationInfo;
    	
    }

    //系统登出后 会自动调用以下方法清理授权和认证缓存
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
