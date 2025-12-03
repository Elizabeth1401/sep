package com.findmyclub.Grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: sep3.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FindMyClubProtoServiceGrpc {

  private FindMyClubProtoServiceGrpc() {}

  public static final String SERVICE_NAME = "api_service.com.findmyclub.Grpc.FindMyClubProtoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<main.java.com.findmyclub.Grpc.Sep3.RequestProto,
      main.java.com.findmyclub.Grpc.Sep3.ResponseProto> getSendRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendRequest",
      requestType = main.java.com.findmyclub.Grpc.Sep3.RequestProto.class,
      responseType = main.java.com.findmyclub.Grpc.Sep3.ResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<main.java.com.findmyclub.Grpc.Sep3.RequestProto,
      main.java.com.findmyclub.Grpc.Sep3.ResponseProto> getSendRequestMethod() {
    io.grpc.MethodDescriptor<main.java.com.findmyclub.Grpc.Sep3.RequestProto, main.java.com.findmyclub.Grpc.Sep3.ResponseProto> getSendRequestMethod;
    if ((getSendRequestMethod = FindMyClubProtoServiceGrpc.getSendRequestMethod) == null) {
      synchronized (FindMyClubProtoServiceGrpc.class) {
        if ((getSendRequestMethod = FindMyClubProtoServiceGrpc.getSendRequestMethod) == null) {
          FindMyClubProtoServiceGrpc.getSendRequestMethod = getSendRequestMethod =
              io.grpc.MethodDescriptor.<main.java.com.findmyclub.Grpc.Sep3.RequestProto, main.java.com.findmyclub.Grpc.Sep3.ResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  main.java.com.findmyclub.Grpc.Sep3.RequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  main.java.com.findmyclub.Grpc.Sep3.ResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new FindMyClubProtoServiceMethodDescriptorSupplier("SendRequest"))
              .build();
        }
      }
    }
    return getSendRequestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FindMyClubProtoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FindMyClubProtoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FindMyClubProtoServiceStub>() {
        @Override
        public FindMyClubProtoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FindMyClubProtoServiceStub(channel, callOptions);
        }
      };
    return FindMyClubProtoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FindMyClubProtoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FindMyClubProtoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FindMyClubProtoServiceBlockingStub>() {
        @Override
        public FindMyClubProtoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FindMyClubProtoServiceBlockingStub(channel, callOptions);
        }
      };
    return FindMyClubProtoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FindMyClubProtoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FindMyClubProtoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FindMyClubProtoServiceFutureStub>() {
        @Override
        public FindMyClubProtoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FindMyClubProtoServiceFutureStub(channel, callOptions);
        }
      };
    return FindMyClubProtoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sendRequest(main.java.com.findmyclub.Grpc.Sep3.RequestProto request,
        io.grpc.stub.StreamObserver<main.java.com.findmyclub.Grpc.Sep3.ResponseProto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendRequestMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FindMyClubProtoService.
   */
  public static abstract class FindMyClubProtoServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return FindMyClubProtoServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FindMyClubProtoService.
   */
  public static final class FindMyClubProtoServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FindMyClubProtoServiceStub> {
    private FindMyClubProtoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FindMyClubProtoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FindMyClubProtoServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendRequest(main.java.com.findmyclub.Grpc.Sep3.RequestProto request,
        io.grpc.stub.StreamObserver<main.java.com.findmyclub.Grpc.Sep3.ResponseProto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendRequestMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FindMyClubProtoService.
   */
  public static final class FindMyClubProtoServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FindMyClubProtoServiceBlockingStub> {
    private FindMyClubProtoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FindMyClubProtoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FindMyClubProtoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public main.java.com.findmyclub.Grpc.Sep3.ResponseProto sendRequest(main.java.com.findmyclub.Grpc.Sep3.RequestProto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendRequestMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FindMyClubProtoService.
   */
  public static final class FindMyClubProtoServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FindMyClubProtoServiceFutureStub> {
    private FindMyClubProtoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FindMyClubProtoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FindMyClubProtoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<main.java.com.findmyclub.Grpc.Sep3.ResponseProto> sendRequest(
        main.java.com.findmyclub.Grpc.Sep3.RequestProto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendRequestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_REQUEST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_REQUEST:
          serviceImpl.sendRequest((main.java.com.findmyclub.Grpc.Sep3.RequestProto) request,
              (io.grpc.stub.StreamObserver<main.java.com.findmyclub.Grpc.Sep3.ResponseProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
                main.java.com.findmyclub.Grpc.Sep3.RequestProto,
                main.java.com.findmyclub.Grpc.Sep3.ResponseProto>(
                service, METHODID_SEND_REQUEST)))
        .build();
  }

  private static abstract class FindMyClubProtoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FindMyClubProtoServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return main.java.com.findmyclub.Grpc.Sep3.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FindMyClubProtoService");
    }
  }

  private static final class FindMyClubProtoServiceFileDescriptorSupplier
      extends FindMyClubProtoServiceBaseDescriptorSupplier {
    FindMyClubProtoServiceFileDescriptorSupplier() {}
  }

  private static final class FindMyClubProtoServiceMethodDescriptorSupplier
      extends FindMyClubProtoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FindMyClubProtoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FindMyClubProtoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FindMyClubProtoServiceFileDescriptorSupplier())
              .addMethod(getSendRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
