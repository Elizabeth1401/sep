package com.findmyclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.findmyclub.repositories")
@EntityScan(basePackages = "com.findmyclub.model")
public class AuthServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(AuthServiceApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8082"));
        app.run(args);
    }
}
