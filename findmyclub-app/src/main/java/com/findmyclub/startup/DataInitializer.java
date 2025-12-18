package com.findmyclub.startup;

import com.findmyclub.model.Club;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    static CommandLineRunner seedData(ClubRepository clubRepository) {
        return args -> {
            clubRepository.deleteAll();

            System.out.println("Seeding data...");

            //Clubs
            Club club1 = new Club(
                    "Wiggly Melody",
                    "Horsens",
                    "We like talking about music",
                "Music"
            );


            Club club2 = new Club(
                    "Boxing Bastards",
                    "Copenhagen",
                    "We like hitting each other",
                "Sports"
            );

            Club club3 = new Club(
                    "Dancing Monkeys",
                    "Aarhus",
                    "We like dancing around",
                "Dance"
            );

            clubRepository.saveAll(List.of(club1, club2, club3));
            System.out.println("Seeding completed");
        };
    }
}
