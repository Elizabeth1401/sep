package com.findmyclub.networking.handlers;

import api_service.com.findmyclub.Grpc.Sep3.ActionTypeProto;
import api_service.com.findmyclub.Grpc.Sep3.ClubProto;
import com.findmyclub.service.ClubService;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

public class ClubHandler implements FindMyClubHandler
{
  private final ClubService service;

  public ClubHandler(ClubService service)
  {
    this.service = service;
  }

  @Override
  public Message handle(ActionTypeProto actionType, Object payload)
  {
    Any payloadAny = (Any) payload;

    return switch (actionType)
    {
      case ACTION_LIST -> service.getMany(); // return ClubListProto (Message)

      case ACTION_GET -> {
        ClubProto request = unpackClub(payloadAny);
        yield service.getSingle(request.getId()); // ClubProto
      }

      case ACTION_CREATE -> {
        ClubProto request = unpackClub(payloadAny);
        yield service.create(request); // ClubProto
      }

      case ACTION_UPDATE -> {
        ClubProto request = unpackClub(payloadAny);
        service.update(request);
        yield ClubProto.newBuilder().build(); // or Empty
      }

      case ACTION_DELETE -> {
        ClubProto request = unpackClub(payloadAny);
        service.delete(request.getId());
        yield ClubProto.newBuilder().build(); // or Empty
      }

      default -> throw new IllegalArgumentException("Invalid action type: " + actionType);
    };
  }

  private ClubProto unpackClub(Any payloadAny)
  {
    try
    {
      return payloadAny.unpack(ClubProto.class);
    }
    catch (InvalidProtocolBufferException e)
    {
      throw new RuntimeException("Invalid payload for Club action", e);
    }
  }
}
