package com.findmyclub;

import com.findmyclub.model.ClubDBtable;
import com.findmyclub.repositories.ClubRepository;
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

  @Bean CommandLineRunner demo(ClubRepository clubRepository) {
    return args -> {
      System.out.println(" demo...");
      ClubDBtable clubDBtable1 = new ClubDBtable("Music","Horsens");
      ClubDBtable clubDBtable2 = new ClubDBtable("Box","Copenhagen");
      ClubDBtable clubDBtable3 = new ClubDBtable("Dance","Aarhus");

      clubRepository.save(clubDBtable1);
      clubRepository.save(clubDBtable2);
      clubRepository.save(clubDBtable3);

      var club = clubRepository.findById(11);

      if (club.isPresent()) {
        ClubDBtable clubDBtable = club.get();
        System.out.println("Before update: " + clubDBtable.getName());

        clubDBtable.setName("Updated Art");

        clubRepository.save(clubDBtable);
      }

      clubRepository.deleteAll();


    };
  }
}
