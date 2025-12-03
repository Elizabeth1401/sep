package com.findmyclub.service;

import com.findmyclub.Grpc.Sep3.ClubListProto;
import com.findmyclub.Grpc.Sep3.ClubProto;
import org.springframework.stereotype.Service;

@Service
public interface ClubService
{
  ClubProto create(ClubProto payload);
  void update(ClubProto payload);
  void delete(int id);
  ClubProto getSingle(int id);
  ClubListProto getMany();
}
