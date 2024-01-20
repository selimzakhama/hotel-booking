package com.example.demo.controllers;

import com.example.demo.Services.UserService;
import com.example.demo.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }



    /************************* LOGIN PAGE **************************/
    @GetMapping("/login")
    public String getLogin(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model, HttpSession session){
        if(userService.userLogin(user.getUsername(),user.getPassword()) != null){
            String id_user = String.valueOf(user.getId());
            session.setAttribute("userId",id_user);
            return "redirect:/adminDashboard";
        } else {
            model.addAttribute("errorMsg", "Invalid username or password");
            return "login";
        }

    }


    /************************* REGISTER PAGE **************************/
    @GetMapping("/register")
    public String getRegister(Model model){
        User user = new User();
        model.addAttribute("registerRequest",user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerRequest") User user,Model model){
        User registredUser = userService.registerUser(user.getUsername(),user.getPassword());
        if(registredUser == null){
            model.addAttribute("errorMsg", "Username already exists");
            return "register";
        } else {
            String msg = "Account created Successfully!";
            return "redirect:/login?successMsg=" + msg;
        }
    }








}
