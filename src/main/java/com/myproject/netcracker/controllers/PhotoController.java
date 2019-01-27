package com.myproject.netcracker.controllers;

import com.myproject.netcracker.domain.Picture;
import com.myproject.netcracker.repos.PictureRepo;
import com.sun.javafx.iio.ImageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.io.IOUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


@Controller
public class PhotoController {

    @Autowired
    PictureRepo pictureRepo;

    @Value("${upload.path}")
    private String uploadPath;


    @RequestMapping("/img/{filename}")
    public @ResponseBody
    byte[] getImage(@PathVariable String filename) throws IOException {

        Picture p = pictureRepo.findByPath(filename);

        String path = uploadPath + "/" + p.getPath();

        InputStream in = getClass()
                .getResourceAsStream(path);

        System.out.println(path);

        File file = new File(path);
        return Files.readAllBytes(file.toPath());
        // return IOUtils.toByteArray(in);
    }


    @RequestMapping("/css/{filename}")
    public @ResponseBody
    byte[] getCss(@PathVariable String filename) throws IOException {


        String path = "/Users/aleksandr/IdeaProjects/netcrackerProjectMVC/src/main/resources/css/" + filename;
        InputStream in = getClass()
                .getResourceAsStream(path);
        File file = new File(path);
        return Files.readAllBytes(file.toPath());
        // return IOUtils.toByteArray(in);
    }

    @RequestMapping("/js/{filename}")
    public @ResponseBody
    byte[] getJs(@PathVariable String filename) throws IOException {
        String path = "/Users/aleksandr/IdeaProjects/netcrackerProjectMVC/src/main/resources/js/select.js";
        InputStream in = getClass()
                .getResourceAsStream(path);
        File file = new File(path);
        return Files.readAllBytes(file.toPath());
        // return IOUtils.toByteArray(in);
    }

}
