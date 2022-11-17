package com.billy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    //this is when 404 error happen, will jump to this page
    @RequestMapping("/404")
    public String error(){
        return "404";
    }

    //this is when user unauthorized, will jump to this page
    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthorized(){
        return "You are not authorized!";
    }

}
