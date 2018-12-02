package com.myproject.netcracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/")
    public String indexController(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String loginController(Model model) {
        return "login";
    }
}
