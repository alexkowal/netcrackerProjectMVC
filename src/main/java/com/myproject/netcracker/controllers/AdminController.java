package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.AdvertRepo;
import com.myproject.netcracker.repos.BrandRepo;
import com.myproject.netcracker.repos.ModelRepo;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


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
        model.addAttribute("user", user);
        model.addAttribute("advert", new Advert());
        return "adminPanel";
    }

    @PostMapping("/admin/userban")
    @Transactional
    public String userAdministration(Model model, User user) {
        User userFromDb = userRepo.findByLogin(user.getLogin());

        if (userFromDb.getIsactive())
            userFromDb.setIsactive(false);
        else userFromDb.setIsactive(true);


        List<Advert> adv = advertRepo.findAllByOwnerId(userFromDb.getIdUser());

        if ((!userFromDb.getIsactive()))
            for (Advert advert : adv) {
                advert.setIsactive(false);
                entityManager.merge(advert);
            }
        else
            for (Advert advert : adv) {
                advert.setIsactive(true);
                entityManager.merge(advert);
            }

        entityManager.merge(userFromDb);
        return "redirect:/admin";
    }

    @PostMapping("/admin/advban")
    @Transactional
    public String advAdministration(Model model, Advert advert) {

        Advert advFromDb = advertRepo.findByAdvId(advert.getAdvId());
        if (advFromDb.getIsactive())
            advFromDb.setIsactive(false);
        else advFromDb.setIsactive(true);
        entityManager.merge(advFromDb);
        return "redirect:/admin";
    }

    @GetMapping("/advban/{id}")
    @Transactional
    public String advBan(@PathVariable Long id, @RequestHeader(required = false) String referer, RedirectAttributes redirectAttributes) {
        Advert advFromDb = advertRepo.findByAdvId(id);
        if (advFromDb.getIsactive())
            advFromDb.setIsactive(false);
        else advFromDb.setIsactive(true);
        entityManager.merge(advFromDb);


        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));
        return "redirect:" + components.getPath();
        //return "redirect:/advban/" + id;
    }

}
