package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserRepo userRepo;


    @RequestMapping({"/","/index"})
    public String indexController(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String loginController(Model model) {
        return "login";
    }

    @RequestMapping("/logout")
    public String logoutController(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerController(Model model) {

        model.addAttribute("UserForm",new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, Map<String,Object> model){
      User userFromDB =   userRepo.findByLogin(user.getLogin());
        if(userFromDB!=null) {
            model.put("message", "user exist");
        return "register";
        }
        user.setId_role(1l);
        //user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
