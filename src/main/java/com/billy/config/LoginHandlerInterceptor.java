package com.billy.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    //Add a login interceptor to check if the user already log in
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //if already log in, there should be an attribute named "loginUser" in the session
        Object loginUser = request.getSession().getAttribute("loginUser");
        //if login user is null means current user not login, so back to login page
        if(loginUser==null){
            request.setAttribute("msg","Please Login First!");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
