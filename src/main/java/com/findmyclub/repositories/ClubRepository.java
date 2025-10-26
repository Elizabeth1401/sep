package com.findmyclub.repositories;

import com.findmyclub.model.ClubDBtable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<ClubDBtable, Long>
{}
