package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class LkController {
    @Autowired
    private UserRepo userRepo;


    @RequestMapping("/lk")
    public String lkController(Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);

        model.put("username", user.getUserName());
        model.put("login", user.getLogin());
        model.put("email", user.getEmail());
        return "lk";
    }
}
