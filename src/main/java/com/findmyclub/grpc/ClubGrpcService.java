package com.findmyclub.grpc;

import com.findmyclub.service.ClubService;
import io.grpc.stub.StreamObserver;

import com.findmyclub.grpc.ClubServiceGrpc;
import com.findmyclub.grpc.ClubList;

import com.google.protobuf.Empty;
import org.lognet.springboot.grpc.GRpcService;

import java.util.List;

@GRpcService
public class ClubGrpcService extends ClubServiceGrpc.ClubServiceImplBase {

  private final ClubService clubService;

  public ClubGrpcService(ClubService clubService) {
    this.clubService = clubService;
  }

  @Override
  public void getAllClubs(Empty request, StreamObserver<ClubList> responseObserver) {
    List<com.findmyclub.model.Club> clubs = clubService.getAllClubs();

    ClubList.Builder listBuilder = ClubList.newBuilder();
    for (com.findmyclub.model.Club c : clubs) {
      listBuilder.addClubs(
          com.findmyclub.grpc.Club.newBuilder()
              .setId(c.getId())
              .setName(c.getName())
              .setLocation(c.getLocation() == null ? "" : c.getLocation())
              .build()
      );
    }

    responseObserver.onNext(listBuilder.build());
    responseObserver.onCompleted();
  }
}