package com.myproject.netcracker.controllers;

import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;


    @RequestMapping("/login")
    public String loginController(Model model) {
        return "login";
    }

    @RequestMapping("/logout")
    public String logoutController(Model model) {
        return "index";
    }

}
