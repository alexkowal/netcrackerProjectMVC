package com.myproject.netcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class NetcrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetcrackerApplication.class, args);
    }
}
