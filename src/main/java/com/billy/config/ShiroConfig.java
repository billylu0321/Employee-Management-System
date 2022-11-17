package com.billy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /*
   this config class is to get the three core beans in shiro, which are securityManager, UserRealm and ShiroDialect.
     */
    //3.get shiro filter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //connect with securityManager, set security manage property
        bean.setSecurityManager(securityManager);

        //use map to set up the url interceptor
        Map<String,String> filterMap = new LinkedHashMap<>();
        //here is authorization. Eg. address /emp must have permission 'admin'
        filterMap.put("/emp","perms[admin]");
        filterMap.put("/delete","perms[admin]");
        filterMap.put("/updateEmp","perms[admin]");
        /*
        here set up authentication, all the request to /emps must first complete the authentication process, after the authentication,
        will authorize the different permissions
         */
        filterMap.put("/emps","authc");
        bean.setFilterChainDefinitionMap(filterMap);

        //if not been authenticated, will jump to the login page, here set up the login page url
        bean.setLoginUrl("/index.html");
        //if already log in but does not has the authorization, will jump to unauthorized page, here set up the un authorized page url
        bean.setUnauthorizedUrl("/unauth");
        return bean;
    }



    //2.get the securityManager object
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //integrate UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //1. create a realm object, need to first customized a UserRealm class
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //use to integrated thymleaf and shiro
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
