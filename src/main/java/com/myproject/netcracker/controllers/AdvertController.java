package com.myproject.netcracker.controllers;


import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Model;
import com.myproject.netcracker.domain.Picture;
import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.AdvertRepo;
import com.myproject.netcracker.repos.PictureRepo;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdvertController {

    @Autowired
    AdvertRepo advertRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PictureRepo pictureRepo;

    @GetMapping("/showadv/{id}")
    public String showadv(@PathVariable Long id, org.springframework.ui.Model model) {
        Advert adv = advertRepo.findByAdvId(id);
        User user = userRepo.findByIdUser(adv.getOwnerId());
        List<Picture> pict= pictureRepo.findByAdvertId(adv.getAdvId());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser")
            if (auth != null)
                if (userRepo.findByLogin(auth.getName()).getIdRole() == 2)
                    model.addAttribute("admin", true);
                else
                    model.addAttribute("admin", false);
            else
                model.addAttribute("admin", false);
        else
            model.addAttribute("admin", false);


        model.addAttribute("user",user);
        model.addAttribute("advert",adv);
        model.addAttribute("pictures",pict);
        return "advert";
    }
}
