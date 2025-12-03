package main.java.com.findmyclub.service;

import main.java.com.findmyclub.Grpc.Sep3.ClubListProto;
import main.java.com.findmyclub.Grpc.Sep3.ClubProto;
import main.java.com.findmyclub.model.Club;
import main.java.com.findmyclub.repositories.ClubRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceDatabase implements ClubService
{
  private ClubRepository repository;
  public ClubServiceDatabase(ClubRepository repository){
    this.repository = repository;
  }
  @Override
  @Transactional //Allowed to make changes to the database
  public ClubProto create(ClubProto payload) {
    Club club =new Club();
    //set values
    club.setName(payload.getName());
    club.setLocation(payload.getLocation());
    //save entity
    Club created = repository.save(club);
    //creating the proto
    return ClubProto.newBuilder()
        .setId(created.getId())
        .setName(created.getName())
        .setLocation(created.getLocation())
        .build();
  }
  @Transactional
  @Override
  public void update(ClubProto payload) {
    Club club =new Club();
    //set values
    //if it has the same id as another entity in the database it updates instead of save
    club.setId(payload.getId());
    club.setName(payload.getName());
    club.setLocation(payload.getLocation());
    //update entity with the same id
    repository.save(club);
  }
  @Transactional
  @Override
  public void delete(int id) {
    //delete entity with id
    repository.deleteById(id);
  }

  @Override public ClubProto getSingle(int id) {
    Optional<Club> fetched = repository.findById(id); //Optinal to prevent null pointer
    Club club = fetched.orElseThrow(() -> new RuntimeException("Club not found"));
    //return proto
    return ClubProto.newBuilder()
        .setId(club.getId())
        .setName(club.getName())
        .setLocation(club.getLocation())
        .build();
  }

  @Override public ClubListProto getMany() {
    List<Club> clubs = repository.findAll();

    // Builder for the list
    ClubListProto.Builder clubProtoBuilder = ClubListProto.newBuilder();

    // Convert each Club entity to ClubProto
    for (Club club : clubs) {
      ClubProto clubProto = ClubProto.newBuilder()
          .setId(club.getId())
          .setName(club.getName())
          .setLocation(club.getLocation())
          .build();
      clubProtoBuilder.addClubs(clubProto);
    }
    // Build and return the list
    return clubProtoBuilder.build();
  }
}
