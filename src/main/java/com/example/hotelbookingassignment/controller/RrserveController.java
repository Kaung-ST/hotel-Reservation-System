package com.example.hotelbookingassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RrserveController {
    @GetMapping("/rooms")
    public String showRoom(){
        return "roomList";
    }
}
