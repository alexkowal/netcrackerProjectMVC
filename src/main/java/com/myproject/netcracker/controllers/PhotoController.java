package com.myproject.netcracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class PhotoController {

    @GetMapping("/loadphoto/{path}")
    public @ResponseBody
    byte[] getImage(@PathVariable String path) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(path);
        return IOUtils.toByteArray(in);
    }
}
