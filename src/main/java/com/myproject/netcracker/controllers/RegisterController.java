package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/register")
    public String registerController(Model model) {

        model.addAttribute("User", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("User") @Valid User user,
                          BindingResult bindingResult,Model model) {

        User userFromDB = userRepo.findByLogin(user.getLogin());
        if (userFromDB != null) {
            bindingResult.addError(new FieldError("User","login",
                    "User exist"));}

        if (user.getPassword()!=user.getConfirmpassword()) {
            bindingResult.addError(new FieldError("User","password",
                    "passwords don't matches"));}


        if (bindingResult.hasErrors()) {
            return "register";
        }

        user.setIdRole(1l);
        userRepo.save(user);
        return "redirect:/login";
    }

}
