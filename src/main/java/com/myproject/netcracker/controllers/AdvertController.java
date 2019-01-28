package com.myproject.netcracker.controllers;


import com.myproject.netcracker.domain.*;
import com.myproject.netcracker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class AdvertController {

    @Autowired
    AdvertRepo advertRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    CharactRepo charactRepo;

    @Autowired
    MarkRepo markRepo;

    @Autowired
    PictureRepo pictureRepo;

    @GetMapping("/showadv/{id}")
    public String showadv(@PathVariable Long id, org.springframework.ui.Model model) {
        Advert adv = advertRepo.findByAdvId(id);
        User user = userRepo.findByIdUser(adv.getOwnerId());
        List<Picture> pict = pictureRepo.findByAdvertId(adv.getAdvId());

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


        model.addAttribute("user", user);

        model.addAttribute("brand", brandRepo.findByBrandId(adv.getBrandId()));
        model.addAttribute("model", modelRepo.findByModelId(adv.getModelId()));
        model.addAttribute("charact", charactRepo.findCharactByCharactId(adv.getCharactId()));

        model.addAttribute("advert", adv);
        model.addAttribute("pictures", pict);
        return "advert";
    }


    @GetMapping("/like/{id}")
    @Transactional
    public String like(@PathVariable Long id, @RequestHeader(required = false) String referer, RedirectAttributes redirectAttributes) {

        Advert adv = advertRepo.findByAdvId(id);
        Mark mark = new Mark();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser")
         if (auth != null)
                mark.setUserId(userRepo.findByLogin(auth.getName()).getIdUser());
        mark.setAdvId(id);

        if (markRepo.findByAdvIdAndUserId(id, mark.getUserId()) == null)
            markRepo.save(mark);
        else
            markRepo.deleteById((markRepo.findByAdvIdAndUserId(id, mark.getUserId())).getMarkId());

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));
        return "redirect:" + components.getPath();

    }
}
