package com.findmyclub.startup;

import com.findmyclub.model.Category;
import com.findmyclub.model.Club;
import com.findmyclub.repositories.CategoryRepository;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    static CommandLineRunner seedData(ClubRepository clubRepository, CategoryRepository categoryRepository) {
        return args -> {
            clubRepository.deleteAll();
            categoryRepository.deleteAll();

            System.out.println("Seeding data...");

            //Category
            Category music = categoryRepository.save(new Category("Music"));
            Category sports = categoryRepository.save(new Category("Sports"));
            Category dance  = categoryRepository.save(new Category("Dance"));

            //Clubs
            Club club1 = new Club(
                    "Wiggly Melody",
                    "Horsens",
                    "We like talking about music"
            );
            club1.getCategories().add(music);

            Club club2 = new Club(
                    "Boxing Bastards",
                    "Copenhagen",
                    "We like hitting each other"
            );
            club2.getCategories().add(sports);

            Club club3 = new Club(
                    "Dancing Monkeys",
                    "Aarhus",
                    "We like dancing around"
            );
            club3.getCategories().addAll(Set.of(dance, music));

            clubRepository.saveAll(List.of(club1, club2, club3));
            System.out.println("Seeding completed");
        };
    }
}
