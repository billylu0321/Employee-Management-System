package com.billy.config;

import com.billy.pojo.User;
import com.billy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//custimized UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //authorization
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        /*
        get the current subject, from subject get the current login user object, which is a User object, is also the first
        parameter in the below SimpleAuthenticationInfo() method
         */

        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        //authorize the permission based on the current login user's permission info in the database
        info.addStringPermission(currentUser.getPerms());
        return info;
    }
    //authentication
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //get the user info from the MySql database
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.queryUserByName(token.getUsername());

        //username authentication
        if(user==null){
            return null;//if the user is null, which means no this user, then return null, will catch the UnknownAccountException
        }
        //if find the user, then save in the current session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("loginUser",user.getUsername());

        //shiro will complete the password authentication for us, if user is not null, which means there is this user,
        // then get the password to do the authentication
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
