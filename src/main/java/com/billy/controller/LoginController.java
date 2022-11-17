package com.billy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")//get the input username and password from html page
    public String login(@RequestParam("username")String username, @RequestParam("password")String password,
                        Model model){
        //get current user subject
        Subject currentUser = SecurityUtils.getSubject();
        //save the input username and password into the token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            /*
            here will implement login method, will executed the doGetAuthorizationInfo and doGetAuthenticationInfo methods in UserRealm.
            If no exception been caught, means login succeed.
             */

            currentUser.login(token);
            return "redirect:/main.html";
        } catch (UnknownAccountException e) {//no this username
            model.addAttribute("msg","Wrong Username");
            return "index";
        } catch (IncorrectCredentialsException e) {//wrong password
            model.addAttribute("msg","Wrong Password");
            return "index";
        }
    }
    //logout method
    @RequestMapping("/user/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index.html";
    }

}
