package com.example.hotelbookingassignment.controller;

import com.example.hotelbookingassignment.ds.Guest;
import com.example.hotelbookingassignment.repository.GuestRepository;
import com.example.hotelbookingassignment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AuthService authService;
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
    @GetMapping("/auth/register-new")
    public String getRegisterForm(Model model)
    { model.addAttribute("guest",new Guest());
        return "accountRegister";
    }
    @PostMapping("/auth/register")
    public String register(@RequestParam("fname")String fname,
                           @RequestParam("lname")String lname,
                           @RequestParam("email")String email,
                           @RequestParam("password")String password){
        Guest guest=new Guest(fname,lname);
        guest.setEmail(email);
        guest.setPassword(password);
        authService.register(guest);
        return "redirect:/login";
    }

}
