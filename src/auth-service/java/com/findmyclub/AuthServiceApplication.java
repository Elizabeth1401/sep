package com.findmyclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Map;

@SpringBootApplication
public class AuthServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(AuthServiceApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8081"));
        app.run(args);
    }

}