package com.huang.config;

import com.huang.pojo.user;
import com.huang.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @authour huang
 */
//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {
    private static final transient Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserServiceImpl userservice;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权");
        //授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前的请求对象。查看对象的权限，进行操作。
        user currentUser = (user)principalCollection.getPrimaryPrincipal();
        log.info(currentUser.toString());
        info.addStringPermission(currentUser.getPerm());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行认证");
        //从数据库中取
      UsernamePasswordToken userToken = (UsernamePasswordToken) token;
      user user = userservice.queryUserByName(userToken.getUsername());
      if (user == null){
            //未查询到用户信息
            return null;//抛出异常
      }
      //密码认证，shiro做
      return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
