package com.findmyclub;

import api_service.com.findmyclub.Grpc.Sep3;
import com.findmyclub.model.Club;
import com.findmyclub.repositories.ClubRepository;
import com.findmyclub.service.ClubServiceDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClubServiceDatabaseTest {
    @Mock
    ClubRepository clubRepository;

    @InjectMocks
    ClubServiceDatabase service;

    // Z - Zero case: Test behavior when the collection is empty.
    // Ensures the service correctly handles zero clubs returned from the repository.
    // O - One case: Test the simplest valid input.
    // Ensures the service works for exactly one club and basic logic is correct.
    // M - Many case: Test behavior with multiple clubs.
    // Ensures the service correctly processes lists larger than one item.
    // B - Boundary case: Test edge conditions such as min/max values.
    // Ensures the method behaves correctly at the limits of valid input.
    // I - Interfaces: Verify interaction with repository.
    // Ensures the service calls repository methods correctly (find, save, delete).
    // E - Exception case: Test error scenarios.
    // Ensures the service throws correct exceptions when data is missing or invalid.
    // S - Simple case: Test the normal, expected scenario.
    // Ensures everything works correctly when valid input is provided.

    //-----------------------Create-------------------------
    @Test
    @DisplayName("O-Should create club and return proto with generated ID")
    void create_ShouldSaveAndReturnProto(){
        Sep3.ClubProto payload = Sep3.ClubProto.newBuilder()
                .setName("Chess Club")
                .setLocation("Copenhagen")
                .build();
        Club saved = new Club("Chess Club","Copenhagen", "Just your simple chess club","Sport");
        saved.setId(1); //simulate DB-generated ID

        when(clubRepository.save(any(Club.class))).thenReturn(saved);

        Sep3.ClubProto result = service.create(payload);

        assertEquals(1,result.getId());
        assertEquals("Chess Club", result.getName());
        assertEquals("Copenhagen", result.getLocation());

        verify(clubRepository,times(1)).save(any(Club.class));
    }
    //-----------------------Update-------------------------
    @Test
    @DisplayName("O-Update should call save with correct ID")
    void update_ShouldCallSave(){
        Sep3.ClubProto payload = Sep3.ClubProto.newBuilder()
                .setId(10)
                .setName("Updated")
                .setLocation("Aalborg")
                .build();
        service.update(payload);
        verify(clubRepository,times(1)).save(any(Club.class));
    }

    //-----------------------Delete-------------------------
    @Test
    @DisplayName("O-Delete should call deleteById")
    void delete_ShouldCallRepository(){
        service.delete(5);
        verify(clubRepository,times(1)).deleteById(5);
    }

    //-----------------------Get Single-------------------------
    @Test
    @DisplayName("S-Should return correct club when found")
    void getSingle_ShouldReturnClub(){
        Club club = new Club("Tennis","Odense", "We like playing with balls","Sport");
        club.setId(3);
        when(clubRepository.findById(3)).thenReturn(Optional.of(club));

        Sep3.ClubProto result = service.getSingle(3);

        assertEquals(3,result.getId());
        assertEquals("Tennis", result.getName());
        assertEquals("Odense", result.getLocation());
    }

    @Test
    @DisplayName("E-Should throw when club not found")
    void getSingle_ShouldThrowIfNotFound(){
        when(clubRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getSingle(99));
        assertEquals("Club not found", ex.getMessage());
    }

    //-----------------------Get Many-------------------------
    @Nested
    @DisplayName("getMany - ZOMBIES")
    class GetMany {
        @Test
        @DisplayName("Z-Should return empty list when no clubs exist")
        void zeroCase_EmptyList(){
            when(clubRepository.findAll()).thenReturn(List.of());

            Sep3.ClubListProto result = service.getMany();

            assertEquals(0, result.getClubsCount());
        }

        @Test
        @DisplayName("O-Should return one club")
        void oneCase_OneClub(){
            Club club = new Club("Chess","Aalborg", "We are superior breed of people","Sport");
            club.setId(6);
            List<Club> clubs = List.of(club);

            when(clubRepository.findAll()).thenReturn(clubs);
            Sep3.ClubListProto result = service.getMany();

            assertEquals(1, result.getClubsCount());
            assertEquals("Chess", result.getClubs(0).getName());
            assertEquals("Aalborg", result.getClubs(0).getLocation());
            assertEquals(6, result.getClubs(0).getId());
        }

        @Test
        @DisplayName("M-Should return many clubs")
        void manyCase_MultipleClubs(){
            // Since JPA usually generates the ID, we manually set it in tests.
            Club club1 = new Club("Chess", "Aalborg", "We are superior breed of people","Sport");
            club1.setId(1);  // simulate DB-generated ID

            Club club2 = new Club("Football", "Aarhus", "We like hitting balls","Sport");
            club2.setId(2);  // simulate DB-generated ID

            List<Club> clubs = List.of(club1,club2);

            when(clubRepository.findAll()).thenReturn(clubs);

            Sep3.ClubListProto result = service.getMany();

            assertEquals(2, result.getClubsCount());

            assertEquals("Chess", result.getClubs(0).getName());
            assertEquals("Aalborg", result.getClubs(0).getLocation());
            assertEquals(1, result.getClubs(0).getId());

            assertEquals("Football", result.getClubs(1).getName());
            assertEquals("Aarhus", result.getClubs(1).getLocation());
            assertEquals(2, result.getClubs(1).getId());
        }

        @Test
        @DisplayName("I-Should interact with repository")
        void interface_CheckFindAllCalled(){
            when(clubRepository.findAll()).thenReturn(List.of());

            service.getMany();

            verify(clubRepository,times(1)).findAll();
        }
    }
}
