package com.findmyclub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class FindMyClubApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(FindMyClubApplication.class, args);
  }

  @Bean CommandLineRunner testDatabaseConnection(JdbcTemplate jdbcTemplate) {
    return args -> {
      System.out.println(" Checking database connection...");
      try {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables", Integer.class);
        System.out.println(" Database connection OK! Table count = " + count);
      } catch (Exception e) {
        System.err.println(" Database connection FAILED: " + e.getMessage());
      }
    };
  }
}
