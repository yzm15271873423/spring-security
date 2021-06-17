package com.yzm.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 16:05
 */

@Controller
public class DemoController {

    //@Secured("ROLE_管理员")
    @PreAuthorize("hasAuthority('demo:update')")
    @ResponseBody
    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("/showMain")
    public String showMain(){
        return "main";
    }

    @RequestMapping("/showFail")
    public String showFail(){
        return "fail";
    }

    @RequestMapping("/AccessDenied")
    public String AccessDenied(){
        return "AccessDenied";
    }
}
