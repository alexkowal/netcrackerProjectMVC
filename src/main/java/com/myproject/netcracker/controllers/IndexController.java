package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Filter;
import com.myproject.netcracker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Controller
@SessionAttributes("filter")
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


    @ModelAttribute
    public Filter createFilter() {
        return new Filter();
    }

    @GetMapping({"/", "/index"})
    public String noFilter(@ModelAttribute("filter") Filter myfilter, Model model) {

        Set<Advert> adverts = new HashSet<>();


        if (myfilter.getBrandId() != null) {
            // adverts.addAll(advertRepo.findAllByBrandId(myfilter.getBrandId()));
            adverts.addAll(advertRepo.findAllByBrandId(myfilter.getBrandId()));
        }

        if (myfilter.getModelId() != null) {
            // adverts.addAll(advertRepo.findAllByModelId(myfilter.getModelId()));
            adverts.addAll(advertRepo.findAllByModelId(myfilter.getModelId()));
        }

        if (myfilter.getCharactId() != null) {
            // adverts.addAll(advertRepo.findAllByCharactId(myfilter.getCharactId()));
            adverts.addAll(advertRepo.findAllByCharactId(myfilter.getCharactId()));
        }

        for (Advert advert : adverts) {
            if (advert.getBrandId() != null && myfilter.getBrandId() != null)
                if (advert.getBrandId() != myfilter.getBrandId())
                    adverts.remove(advert);

            if (advert.getModelId() != null && myfilter.getModelId() != null)
                if (advert.getModelId() != myfilter.getModelId())
                    adverts.remove(advert);

            if (advert.getCharactId() != null && myfilter.getCharactId() != null)
                if (advert.getCharactId() != myfilter.getCharactId())
                    adverts.remove(advert);


            if (advert.getMileage() != null && myfilter.getMinMileage() != null && myfilter.getMaxMileage()!=null)
                if (advert.getMileage() <= myfilter.getMinMileage() || advert.getMileage() >= myfilter.getMaxMileage() )
                    adverts.remove(advert);


            if (advert.getBrandId() != null && myfilter.getBrandId() != null)
                if (advert.getBrandId() != myfilter.getBrandId())
                    adverts.remove(advert);


        }

        /*if (adverts.isEmpty())
            adverts.addAll(advertRepo.findAllByAddDateIsNotNull());
*/




        model.addAttribute("adverts", adverts);
        model.addAttribute("brands", brandRepo);
        model.addAttribute("model", modelRepo.findAllByBrandId(myfilter.getBrandId()));
        model.addAttribute("charact", charactRepo.findAllByBrandIdAndAndModelId(myfilter.getBrandId(), myfilter.getModelId()));

        return "index";
    }

    @PostMapping({"/", "/index"})
    public String filter(@ModelAttribute("filter") Filter myfilter) {
        System.out.println(myfilter.getBrandId());
        return "redirect:";
    }

    @GetMapping({"/resetfilter"})
    public String dropfilter(@ModelAttribute("filter") Filter myfilter, WebRequest request,
                             SessionStatus sessionStatus) {
        myfilter.remove();
        sessionStatus.setComplete();
        request.removeAttribute("filter", WebRequest.SCOPE_SESSION);
        return "redirect:";
    }


}
