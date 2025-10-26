package com.findmyclub.service;

import com.findmyclub.model.Club;
import com.findmyclub.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService
{
  private final ClubRepository clubRepository;

  public ClubService(ClubRepository clubRepository)
  {
    this.clubRepository = clubRepository;
  }

  public List<Club> getAllClubs()
  {
    return clubRepository.findAll();
  }
}
