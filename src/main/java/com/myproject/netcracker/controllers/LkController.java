package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Mark;
import com.myproject.netcracker.domain.Picture;
import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.AdvertRepo;
import com.myproject.netcracker.repos.MarkRepo;
import com.myproject.netcracker.repos.PictureRepo;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LkController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    AdvertRepo advertRepo;

    @Autowired
    PictureRepo pictureRepo;

    @Autowired
    MarkRepo markRepo;

    @RequestMapping("/lk")
    public String lkController(Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        List<Advert> usersAdv = advertRepo.findAllByOwnerId(user.getIdUser());

        List<Picture> pictures = new ArrayList<>();
        for (Advert advert : usersAdv) {
            pictures.addAll(pictureRepo.findByAdvertId(advert.getAdvId()));
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser")
            if (userRepo.findByLogin(auth.getName()).getIdRole() == 2) {
                model.put("admin", true);
                model.put("userId", userRepo.findByLogin(auth.getName()).getIdUser());
            } else {
                model.put("admin", false);
                model.put("userId", userRepo.findByLogin(auth.getName()).getIdUser());
            }
        else
            model.put("admin", false);

        model.put("marks", markRepo.findAllByUserId(user.getIdUser()));


        List<Picture> markPict = new ArrayList<>();
        for (Mark mark : markRepo.findAllByUserId(user.getIdUser())) {
            markPict.addAll(pictureRepo.findByAdvertId(mark.getAdvId()));
        }
        model.put("markPict", markPict);


        model.put("username", user.getUserName());
        model.put("advertRepo", advertRepo);
        model.put("login", user.getLogin());
        model.put("email", user.getEmail());
        model.put("pictures", pictures);
        model.put("adverts", usersAdv);
        return "lk";
    }

}
