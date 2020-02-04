package com.sabulous.todoapp.controllers;

import com.sabulous.todoapp.services.IUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class MainController {

    @Autowired
    private IUserDetailsService userDetailsService;

    @GetMapping
    public String getHome(Authentication authentication) {
        if(authentication == null) {
            System.out.println("get HOME: auth null");
            return "You need to login first";
        }
        System.out.println("get HOME: auth OK");
        return "Welcome Home page";
    }

    @GetMapping(value={"login"})
    public String getLoginPage(Authentication authentication) {
        if(authentication != null) {
            System.out.println("get LOGIN : auth null");
            return "home";
        }
        System.out.println("get LOGIN : auth OK");
        return "login";
    }

    @GetMapping(value={"loggedIn"})
    public boolean loggedIn() {
        return userDetailsService.getLoggedInUser() != null;
    }
}