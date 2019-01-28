package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.*;
import com.myproject.netcracker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("advert")
public class NewAdwController {

    @Value("${upload.path}")
    private String uploadPath;

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
    public String startAdwController(Model model) {

        Integer id = 0;
        List<Brand> list = brandRepo.findAllByBrandIdIsNotNull();
        Advert advert = new Advert();
        model.addAttribute("brands", list);
        model.addAttribute("id", id);
        LocalDate date = LocalDate.now();
        advert.setAddDate(date);
        model.addAttribute("advert", advert);
        return "newadv";
    }

    @ModelAttribute("advert")
    public Advert createAdvert() {
        return new Advert();
    }


    @PostMapping("/newadv")
    public String getBrand(@Valid @ModelAttribute("advert") Advert advert, BindingResult bindingResultAdv, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        advert.setOwnerId(user.getIdUser());


        System.out.println("/newadv/" + advert.getBrandId());
        model.addAttribute("br", bindingResultAdv);

        if (advert.getBrandId() == null)
            bindingResultAdv.addError(new FieldError(bindingResultAdv.getObjectName(), "brandId",
                    "Выберите марку"));

        if (advert.getDescription().isEmpty())
            bindingResultAdv.addError(new FieldError("advert", "description",
                    "Введите описание"));
        if (advert.getTitle().isEmpty())
            bindingResultAdv.addError(new FieldError("advert", "title",
                    "Введите название"));


        if (bindingResultAdv.hasErrors()) {
            if ((advert.getBrandId() == null) || (advert.getTitle().isEmpty()) || (advert.getDescription().isEmpty())) {
                advert.setBrandId(null);
                advert.setTitle(null);
                advert.setDescription(null);
                return "redirect:/newadv";
            }

        }

        return "redirect:newadv/" + advert.getBrandId();
    }


    @GetMapping("/newadv/{brandParam}")
    public String paramAdwController(@ModelAttribute(name = "Advert") Advert advert,
                                     Model model, @PathVariable String brandParam, SessionStatus status) {

        List<com.myproject.netcracker.domain.Model> list = modelRepo.findAllByBrandId(Long.valueOf(brandParam));

        model.addAttribute("brandRepo", brandRepo);

        model.addAttribute("models", list);
        System.out.println("id = " + brandParam);
        return "newadv";

    }


    @PostMapping("/newadv/{brandParam}")
    public String getChar(@ModelAttribute("advert") @Valid Advert advert, BindingResult bindingResultAdv,
                          Model model) {
        String returnVal = "redirect:newadv/" + advert.getBrandId() + "/" + advert.getModelId();
        System.out.println(returnVal);

        if (advert.getModelId() == null)
            bindingResultAdv.addError(new FieldError("advert", "modelId",
                    "Выберите модель"));


        if (bindingResultAdv.hasErrors()) {
            if ((advert.getModelId() == null)) {
                advert.setModelId(null);
                return "redirect:" + advert.getBrandId();
            }
        }

        return "redirect:" + advert.getBrandId() + "/" + advert.getModelId();

    }

    @GetMapping("/newadv/{brandParam}/{modelParam}")
    public String lastAdwController(@ModelAttribute(name = "advert") Advert advert,
                                    Model model, @PathVariable String brandParam,
                                    @PathVariable String modelParam, SessionStatus status) {

        List<Charact> list = charactRepo.findAllByBrandIdAndAndModelId(Long.valueOf(brandParam), Long.valueOf(modelParam));

        model.addAttribute("brandRepo", brandRepo);
        model.addAttribute("modelRepo", modelRepo);
        model.addAttribute("charact", list);
        return "newadv";

    }


    @PostMapping("/newadv/{brandParam}/{modelParam}")
    public String saveAdv(@ModelAttribute(name = "advert") @Valid Advert advert, BindingResult bindingResultAdv,
                          Model model, @PathVariable String brandParam,
                          @PathVariable String modelParam, SessionStatus status) {

        advert.setIsactive(true);
        //return "redirect:/lk";

        if (advert.getCharactId() == null)
            bindingResultAdv.addError(new FieldError("advert", "charactId",
                    "Выберите комплектацию"));


        if (advert.getCharactId() == null || advert.getCostVal() == null || advert.getMileage() == null || advert.getFactYear() == null)
            bindingResultAdv.addError(new FieldError("advert", "modelId",
                    "Выберите модель"));


        if (bindingResultAdv.hasErrors()) {
            if (advert.getCharactId() == null || advert.getCostVal() == null || advert.getMileage() == null || advert.getFactYear() == null) {
                advert.setCharactId(null);
                advert.setCostVal(null);
                advert.setMileage(null);
                advert.setFactYear(null);
                return "redirect:/newadv/" + advert.getBrandId() + "/" + advert.getModelId();
            }
            return "redirect:/newadv/" + advert.getBrandId() + "/" + advert.getModelId();
        }


        advertRepo.save(advert);
        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String upload(@ModelAttribute(name = "advert") Advert advert,
                         Model model,
                         MultipartFile file1,
                         MultipartFile file2,
                         MultipartFile file3,
                         MultipartFile file4,
                         MultipartFile file5) {
        model.addAttribute("file1", file1);
        model.addAttribute("file1", file2);
        model.addAttribute("file1", file3);
        model.addAttribute("file1", file4);
        model.addAttribute("file1", file5);

        return "photoUpload";
    }

    @PostMapping("/upload")
    public String postUpload(@ModelAttribute(name = "advert") Advert advert, Model model,
                             @ModelAttribute(name = "file1") MultipartFile file1,
                             @ModelAttribute(name = "file2") MultipartFile file2,
                             @ModelAttribute(name = "file3") MultipartFile file3,
                             @ModelAttribute(name = "file4") MultipartFile file4,
                             @ModelAttribute(name = "file5") MultipartFile file5,
                             SessionStatus status) throws IOException {


        if (file1.getOriginalFilename().length() > 2) {
            Picture pict1 = new Picture();
            pict1.setAdvertId(advert.getAdvId());

            File direct = new File(uploadPath);
            if (!direct.exists())
                direct.mkdir();

            String uuidFile = UUID.randomUUID().toString();

            String result = uuidFile + "." + file1.getOriginalFilename();

            pict1.setPath(result);

            file1.transferTo(new File(uploadPath + "/" + result));
            pictureRepo.save(pict1);
        }
///////////FILE2
///////////FILE2
///////////FILE2

        if (file2.getOriginalFilename().length() > 2) {
            Picture pict2 = new Picture();
            pict2.setAdvertId(advert.getAdvId());

            File direct = new File(uploadPath);
            if (!direct.exists())
                direct.mkdir();

            String uuidFile = UUID.randomUUID().toString();

            String result = uuidFile + "." + file2.getOriginalFilename();

            pict2.setPath(result);

            file2.transferTo(new File(uploadPath + "/" + result));
            pictureRepo.save(pict2);
        }

        if (file3.getOriginalFilename().length() > 2) {
            Picture pict3 = new Picture();
            pict3.setAdvertId(advert.getAdvId());

            File direct = new File(uploadPath);
            if (!direct.exists())
                direct.mkdir();

            String uuidFile = UUID.randomUUID().toString();

            String result = uuidFile + "." + file3.getOriginalFilename();

            pict3.setPath(result);

            file3.transferTo(new File(uploadPath + "/" + result));
            pictureRepo.save(pict3);
        }

        if (file4.getOriginalFilename().length() > 2) {

            Picture pict4 = new Picture();

            System.out.println("@@@@@@@@@@@" + file4.getOriginalFilename());

            pict4.setAdvertId(advert.getAdvId());

            File direct = new File(uploadPath);
            if (!direct.exists())
                direct.mkdir();

            String uuidFile = UUID.randomUUID().toString();

            String result = uuidFile + "." + file4.getOriginalFilename();

            pict4.setPath(result);

            file4.transferTo(new File(uploadPath + "/" + result));
            pictureRepo.save(pict4);
        }

        if (file5.getOriginalFilename().length() > 2) {
            Picture pict5 = new Picture();
            pict5.setAdvertId(advert.getAdvId());

            File direct = new File(uploadPath);
            if (!direct.exists())
                direct.mkdir();

            String uuidFile = UUID.randomUUID().toString();

            String result = uuidFile + "." + file5.getOriginalFilename();

            pict5.setPath(result);

            file5.transferTo(new File(uploadPath + "/" + result));

            pictureRepo.save(pict5);

        }
        status.setComplete();
        advertRepo.save(advert);

        return "redirect:index";
    }

    @GetMapping({"/resetadv"})
    public String dropadv(@ModelAttribute("advert") Advert advert, WebRequest request,
                          SessionStatus sessionStatus) {

        advert.remove();
        sessionStatus.setComplete();
        request.removeAttribute("advert", WebRequest.SCOPE_SESSION);
        return "redirect:";

    }

}
