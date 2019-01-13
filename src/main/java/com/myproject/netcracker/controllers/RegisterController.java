package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                          BindingResult bindingResult, Model model) {

        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

        User userFromDB = userRepo.findByLogin(user.getLogin());
        if (userFromDB != null) {
            bindingResult.addError(new FieldError("User", "login",
                    "Такой пользователь уже существует"));
        }

        if (user.getPassword().length() > 32 || user.getPassword().length() < 8)
            bindingResult.addError(new FieldError("User", "password",
                    "Длина пароля должна быть от 8 до 32 символов"));

        if (user.getConfirmpassword().length() > 32 || user.getConfirmpassword().length() < 8)
            bindingResult.addError(new FieldError("User", "password",
                    "Длина пароля должна быть от 8 до 32 символов"));


        if (!user.getPassword().equals(user.getConfirmpassword())) {
            bindingResult.addError(new FieldError("User", "password",
                    "Пароли не совпадают"));
        }


        if (bindingResult.hasErrors()) {
            return "register";
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setConfirmpassword(bcryptEncoder.encode(user.getConfirmpassword()));

        user.setIdRole(1l);
        userRepo.save(user);
        return "redirect:/login";
    }

}
