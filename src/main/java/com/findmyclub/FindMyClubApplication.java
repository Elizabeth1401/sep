package com.findmyclub;

import com.findmyclub.model.Club;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FindMyClubApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(FindMyClubApplication.class, args);
  }

//  @Bean CommandLineRunner demo(ClubRepository clubRepository) {
//    return args -> {
//      System.out.println(" demo...");
//      Club club1 = new Club("Music","Horsens");
//      Club club2 = new Club("Box","Copenhagen");
//      Club club3 = new Club("Dance","Aarhus");
//
//      clubRepository.save(club1);
//      clubRepository.save(club2);
//      clubRepository.save(club3);
//
//      var club = clubRepository.findById(11);
//
//      if (club.isPresent()) {
//        Club clubDBtable = club.get();
//        System.out.println("Before update: " + clubDBtable.getName());
//
//        clubDBtable.setName("Updated Art");
//
//        clubRepository.save(clubDBtable);
//      }
//
//    };
// }
}
