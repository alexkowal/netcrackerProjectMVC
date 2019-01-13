package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Brand;
import com.myproject.netcracker.domain.Charact;
import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@SessionAttributes("advert")
public class NewAdwController {

    @Autowired
    AdvertRepo advertRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    CharactRepo charactRepo;

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    PictureRepo pictureRepo;


    @GetMapping("/newadv")
    public String startAdwController(@ModelAttribute("advert") Advert advert, Model model) {

        Integer id = 0;
        List<Brand> list = brandRepo.findAllByBrandIdIsNotNull();
        //model.addAttribute("advert", new Advert());
        model.addAttribute("brands", list);
        model.addAttribute("id", id);
        LocalDate date = LocalDate.now();
        advert.setAddDate(date);
        return "newadv";
    }

    @ModelAttribute
    public Advert createAdvert() {
        return new Advert();
    }


    @PostMapping("/newadv")
    public String getBrand(@ModelAttribute("advert") Advert advert, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        advert.setOwnerId(user.getIdUser());

        System.out.println("/newadv/" + advert.getBrandId());
        return "redirect:newadv/" + advert.getBrandId();
    }


    @GetMapping("/newadv/{brandParam}")
    public String paramAdwController(@ModelAttribute(name = "advert") Advert advert,
                                     Model model, @PathVariable String brandParam, SessionStatus status) {

        List<com.myproject.netcracker.domain.Model> list = modelRepo.findAllByBrandId(Long.valueOf(brandParam));
        model.addAttribute("models", list);
        System.out.println("id = " + brandParam);
        return "newadv";

    }


    @PostMapping("/newadv/{brandParam}")
    public String getChar(@ModelAttribute("advert") Advert advert, Model model) {
        String returnVal = "redirect:newadv/" + advert.getBrandId() + "/" + advert.getModelId();
        System.out.println(returnVal);
        return "redirect:" + advert.getBrandId() + "/" + advert.getModelId();

    }

    @GetMapping("/newadv/{brandParam}/{modelParam}")
    public String lastAdwController(@ModelAttribute(name = "advert") Advert advert,
                                    Model model, @PathVariable String brandParam,
                                    @PathVariable String modelParam, SessionStatus status) {

        List<Charact> list = charactRepo.findAllByBrandIdAndAndModelId(Long.valueOf(brandParam), Long.valueOf(modelParam));

        model.addAttribute("charact", list);
        return "newadv";

    }


    @PostMapping("/newadv/{brandParam}/{modelParam}")
    public String saveAdv(@ModelAttribute(name = "advert") Advert advert,
                          Model model, @PathVariable String brandParam,
                          @PathVariable String modelParam, SessionStatus status) {

        advert.setIsactive(true);
        status.setComplete();
        advertRepo.save(advert);
        return "redirect:/lk";
    }

    @GetMapping("/upload")
    public String upload(@ModelAttribute(name = "advert") Advert advert, Model model) {

        return "photoUpload";
    }


    @PostMapping("/upload")
    public String postUpload(@ModelAttribute(name = "advert") Advert advert, Model model) {

        return "photoUpload";
    }

}
