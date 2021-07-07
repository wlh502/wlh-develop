package com.wlh.develop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("登录用户:"+session.getId());
        session.setAttribute("userId", session.getId());
        return "home";
    }
}
