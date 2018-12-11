package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/register")
    public String registerController(Model model) {

        model.addAttribute("UserForm", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = userRepo.findByLogin(user.getLogin());
        if (userFromDB != null) {
            model.put("message", "user exist");
            return "register";
        }
        user.setIdRole(1l);
        userRepo.save(user);
        return "redirect:/login";
    }

}
