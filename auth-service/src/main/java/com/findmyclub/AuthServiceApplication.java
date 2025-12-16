package com.findmyclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.findmyclub.controller",
    "com.findmyclub.service",
    "com.findmyclub.security",
    "com.findmyclub.repositories"
})
@EntityScan(basePackages = "com.findmyclub.model")
@EnableJpaRepositories(basePackages = "com.findmyclub.repositories")
public class AuthServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthServiceApplication.class, args);
  }
}
