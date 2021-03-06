package com.myproject.netcracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ImageConfig {

    @Configuration
    public class ResourceConfig implements WebMvcConfigurer {
        @Value("${upload.path}")
        private String uploadPath;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/img/**")
                    .addResourceLocations("file://" + uploadPath + "/", "/css/", "/resources/"
                            , "file:///Users/aleksandr/IdeaProjects/netcrackerProjectMVC/src/main/resources/css/")/*
                    .addResourceLocations("file:///Users/aleksandr/IdeaProjects/netcrackerProjectMVC/src/main/resources/css/")
                    .addResourceLocations("/resources/")   , "/css/**"   */;
        }
    }
}
