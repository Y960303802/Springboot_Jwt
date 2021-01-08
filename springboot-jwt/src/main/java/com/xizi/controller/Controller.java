package com.xizi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    @GetMapping("/test/test")
    public String test(String username, HttpServletRequest request){
        request.getSession().setAttribute("username",username );
        return "login-ok";
    }
}
