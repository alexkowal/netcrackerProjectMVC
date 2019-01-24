package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Charact;
import com.myproject.netcracker.domain.Filter;
import com.myproject.netcracker.domain.Picture;
import com.myproject.netcracker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    PictureRepo pictureRepo;

    @Autowired
    MarkRepo markRepo;

    @ModelAttribute
    public Filter createFilter() {
        return new Filter();
    }

    @GetMapping({"/", "/index"})
    public String noFilter(@ModelAttribute("filter") Filter myfilter, Model model) {
        Set<Advert> adverts = new HashSet<>();

        adverts.addAll(advertRepo.findAllByAddDateIsNotNull());
        Set<Advert> removeList = new HashSet<>();

        for (Advert advert : adverts) {
            if (!advert.getIsactive())
                removeList.add(advert);
            if (advert.getBrandId() != null && myfilter.getBrandId() != null)
                if (advert.getBrandId() != myfilter.getBrandId())
                    //  adverts.remove(advert);
                    removeList.add(advert);

            if (!advert.getIsactive())
                removeList.add(advert);

            if (advert.getModelId() != null && myfilter.getModelId() != null)
                if (advert.getModelId() != myfilter.getModelId())
                    //adverts.remove(advert);
                    removeList.add(advert);

            if (advert.getCharactId() != null && myfilter.getCharactId() != null)
                if (advert.getCharactId() != myfilter.getCharactId())
                    // adverts.remove(advert);
                    removeList.add(advert);


            if (advert.getMileage() != null && myfilter.getMinMileage() != null && myfilter.getMaxMileage() != null)
                if (advert.getMileage() <= myfilter.getMinMileage() || advert.getMileage() >= myfilter.getMaxMileage())
                    // adverts.remove(advert);
                    removeList.add(advert);


            if (advert.getCostVal() != null && myfilter.getMinCost() != null && myfilter.getMaxCost() != null)
                if (advert.getCostVal() <= myfilter.getMinCost() || advert.getMileage() >= myfilter.getMaxCost())
                    //  adverts.remove(advert);
                    removeList.add(advert);

            Set<Charact> charactByTransmissionTypes = new HashSet<>();
            Set<Charact> charactByBodyTypes = new HashSet<>();
            Set<Charact> charactByDriveUnits = new HashSet<>();

            if (myfilter.getBodyType() != null && myfilter.getBodyType() != "") {
                charactByBodyTypes.addAll(charactRepo.findAllByBody(myfilter.getBodyType()));

                if (!charactByBodyTypes.contains(charactRepo.findCharactByCharactId(advert.getCharactId())))
                    //adverts.remove(advert);
                    removeList.add(advert);
            }

            if (myfilter.getTransmissionType() != null && myfilter.getTransmissionType() != "") {
                charactByTransmissionTypes.addAll(charactRepo.findAllByTransmission(myfilter.getTransmissionType()));
                if (!charactByTransmissionTypes.contains(charactRepo.findCharactByCharactId(advert.getCharactId())))
                    // adverts.remove(advert);
                    removeList.add(advert);
            }


            if (myfilter.getDriveUnite() != null && myfilter.getDriveUnite() != "") {
                charactByDriveUnits.addAll(charactRepo.findAllByDriveUnit(myfilter.getDriveUnite()));
                if (!charactByDriveUnits.contains(charactRepo.findCharactByCharactId(advert.getCharactId())))
                    // adverts.remove(advert);
                    removeList.add(advert);
            }
        }

        for (Advert rem : removeList)
            adverts.remove(rem);


        List<Picture> pictures = new ArrayList<>();
        for (Advert advert : adverts) {
            pictures.addAll(pictureRepo.findByAdvertId(advert.getAdvId()));
        }


        Set<Charact> characts = new HashSet<>();
        characts.addAll(charactRepo.findAllByBrandIdIsNotNull());

        Set<String> transmissionTypes = new HashSet<>();
        Set<String> bodyTypes = new HashSet<>();
        Set<String> driveUnits = new HashSet<>();

        for (Charact c : characts) {
            transmissionTypes.add(c.getTransmission());
            bodyTypes.add(c.getBody());
            driveUnits.add(c.getDriveUnit());
        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser")
            if (userRepo.findByLogin(auth.getName()).getIdRole() == 2) {
                model.addAttribute("admin", true);
                model.addAttribute("userId", userRepo.findByLogin(auth.getName()).getIdUser());
            } else {
                model.addAttribute("admin", false);
                model.addAttribute("userId", userRepo.findByLogin(auth.getName()).getIdUser());
            }
        else
            model.addAttribute("admin", false);


        model.addAttribute("pictures", pictures);
        model.addAttribute("pictRepo", pictureRepo);

        model.addAttribute("adverts", adverts);
        model.addAttribute("brands", brandRepo);

        model.addAttribute("model", modelRepo.findAllByBrandId(myfilter.getBrandId()));
        model.addAttribute("modelRepo", modelRepo);

        model.addAttribute("charact", charactRepo.findAllByBrandIdAndAndModelId(myfilter.getBrandId(), myfilter.getModelId()));
        model.addAttribute("charactRepo", charactRepo);

        model.addAttribute("transmissionTypes", transmissionTypes);
        model.addAttribute("bodyTypes", bodyTypes);
        model.addAttribute("driveUnits", driveUnits);

        model.addAttribute("markRepo", markRepo);

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
