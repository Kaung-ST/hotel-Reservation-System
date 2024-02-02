package com.example.hotelbookingassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @RequestMapping("/account")
    public String account(){
        return "account";
    }
   @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public  String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }
}
