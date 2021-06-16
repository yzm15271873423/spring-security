package com.yzm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 16:05
 */

@Controller
public class DemoController {

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
}
