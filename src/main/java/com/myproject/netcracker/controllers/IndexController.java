package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Brand;
import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.AdvertRepo;
import com.myproject.netcracker.repos.BrandRepo;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private AdvertRepo advertRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private UserRepo userRepo;


    @RequestMapping({"/", "/index"})
    public String indexController(Model model, ArrayList<Advert> adverts) {
        List<Advert> list = advertRepo.findAllByAddDateIsNotNull();
        model.addAttribute("adverts",list);
        model.addAttribute("brand", brandRepo);

        return "index";
    }


}
