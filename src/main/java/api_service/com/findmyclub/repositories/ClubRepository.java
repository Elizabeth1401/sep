package api_service.com.findmyclub.repositories;

import api_service.com.findmyclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer>
{}
