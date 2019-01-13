package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.AdvertRepo;
import com.myproject.netcracker.repos.BrandRepo;
import com.myproject.netcracker.repos.ModelRepo;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Controller
public class AdminController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AdvertRepo advertRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/admin")
    public String administration(Model model) {
        User user = new User();
        String username = "";
        model.addAttribute("username", username);
        model.addAttribute("user", user);
        return "adminPanel";
    }

    @PostMapping("/admin")
    @Transactional
    public String postAdministration(Model model, User user) {
        User userFromDb = userRepo.findByLogin(user.getLogin());

        userFromDb.setIsactive(false);

        entityManager.merge(userFromDb);

      //  userRepo.save(userFromDb);
        return "redirect:/admin";
    }
}
