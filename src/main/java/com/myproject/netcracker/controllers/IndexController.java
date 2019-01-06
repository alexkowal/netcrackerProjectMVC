package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Filter;
import com.myproject.netcracker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("myfilter")
@RequestMapping("/")
public class IndexController {

    @Autowired
    private AdvertRepo advertRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private CharactRepo charactRepo;

    @Autowired
    private UserRepo userRepo;


    @GetMapping({"/", "/index"})
    public String noFilter(@ModelAttribute("filter") Filter myfilter, Model model, ArrayList<Advert> adverts) {
        List<List<Advert>> list = advertRepo.findAllByAddDateIsNotNull();

        if (myfilter.getBrandId() != null)
            list.add(advertRepo.findAllByBrandId(myfilter.getBrandId()));

        if (myfilter.getModelId() != null)
            list.add(advertRepo.findAllByModelId(myfilter.getModelId()));

        if (myfilter.getCharactId() != null)
            list.add(advertRepo.findAllByCharactId(myfilter.getCharactId()));


        if (list.isEmpty())
            list = advertRepo.findAllByAddDateIsNotNull();
        model.addAttribute("adverts", list);
        model.addAttribute("brand", brandRepo);
        model.addAttribute("model", modelRepo);
        model.addAttribute("charact", charactRepo);

        return "index";
    }

    @PostMapping({"/", "/index"})
    public String filter(@ModelAttribute("myfilter") Filter filter, Model model, ArrayList<Advert> adverts) {

      /*  List<Advert> list = advertRepo.findAllByAddDateIsNotNull();
        model.addAttribute("adverts", list);
        model.addAttribute("brand", brandRepo);
      */
        return "redirect:index";
    }


}
