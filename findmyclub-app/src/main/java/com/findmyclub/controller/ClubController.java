package com.findmyclub.controller;

import com.findmyclub.DTO.ClubDTO;
import com.findmyclub.model.Club;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@CrossOrigin(origins = "http://localhost:5173") // BLAZOR MUST BE RUN ON THIS POST, otherwise it won't work
public class ClubController {

    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    //read all
    @GetMapping
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    //create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Club create(@RequestBody ClubDTO clubDTO) {
        Club club = new Club(clubDTO.name, clubDTO.location);
        return clubRepository.save(club);
    }

    // read by id
    @GetMapping("/{id}")
    public Club getOne(@PathVariable int id) {
        return clubRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        if (!clubRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        clubRepository.deleteById(id);
    }

    //update partially
    @PatchMapping("/{id}")
    public Club patch(@PathVariable int id, @RequestBody ClubDTO clubDTO) {
        var existing = clubRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (clubDTO.name != null) existing.setName(clubDTO.name);
        if (clubDTO.location != null) existing.setLocation(clubDTO.location);
        return clubRepository.save(existing);
    }

    //update fully
    @PutMapping("/{id}")
    public Club update(@PathVariable int id, @RequestBody ClubDTO clubDTO) {
        var existing = clubRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        existing.setName(clubDTO.name);
        existing.setLocation(clubDTO.location);
        return clubRepository.save(existing);
    }
}
