package com.huang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @authour huang
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
        添加shiro内置过滤器
        anon ： 无需认证即可访问
        authc：必须认证才可以访问
        user：必须拥有 记住我才可以访问
        perms： 必须拥有某资源的权限才可以访问
        role： 必须拥有某个角色权限才可以访问
         */

        LinkedHashMap<String, String> filtermap = new LinkedHashMap<>();


        //权限控制的操作，正常情况下跳转至授权页面.首先设置需要控制权限的页面，再设置低登记的页面。
        filtermap.put("/user/add", "perms[user:add]");

        filtermap.put("/user/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtermap);

        //设置登录的请求
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        return shiroFilterFactoryBean;
    }

    //DefaultWebSercurityManager
    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象,并且注入IOC托管
    @Bean(name = "userRealm")//不加默认方法名
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
