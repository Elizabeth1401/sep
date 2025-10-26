package com.findmyclub.repositories;

import com.findmyclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer>
{}
