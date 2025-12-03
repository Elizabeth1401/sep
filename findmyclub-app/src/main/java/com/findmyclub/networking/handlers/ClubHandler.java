package com.findmyclub.networking.handlers;

import com.findmyclub.Grpc.Sep3.ActionTypeProto;
import com.findmyclub.Grpc.Sep3.ClubProto;
import com.findmyclub.networking.handlers.FindMyClubHandler;
import com.findmyclub.service.ClubService;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

public class ClubHandler implements FindMyClubHandler
{
  private final ClubService service;
  public ClubHandler (ClubService service)
  {
    this.service=service;
  }

  @Override public Message handle(ActionTypeProto actionType, Object payload)
  {
    Message proto = null;
    Any payloadAny = (Any) payload;
    ClubProto request = null;
    try
    {
      request = payloadAny.unpack(ClubProto.class);
    }
    catch (InvalidProtocolBufferException e)
    {
      throw new RuntimeException(e);
    }
    switch (actionType) {
      case ACTION_GET -> {
        proto = service.getSingle(request.getId());
      }
      case ACTION_CREATE -> {
        proto = service.create(request);
      }
      case ACTION_UPDATE -> {
        service.update(request);
        break;
      }
      case ACTION_DELETE -> {
        service.delete(request.getId());
        break;
      }
      case ACTION_LIST -> {
          proto = service.getMany();
      }
      default -> {
        throw new IllegalArgumentException("Invalid action type: " + actionType);
      }
    }
    //sometimes it will return null, no need to check for that
    //in case of delete
    if (proto == null) {
      proto = ClubProto.newBuilder().build();
    }
    return Any.pack(proto) ;
  }
}
