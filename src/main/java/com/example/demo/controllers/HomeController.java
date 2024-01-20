package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToLogin(){
        return "redirect:/home";
    }

    @GetMapping("/packs")
    public String goToPacks(){
        return "packs";
    }
}
