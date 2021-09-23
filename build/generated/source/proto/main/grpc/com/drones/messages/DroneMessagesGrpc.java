package com.drones.messages;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: DroneMessages.proto")
public final class DroneMessagesGrpc {

  private DroneMessagesGrpc() {}

  public static final String SERVICE_NAME = "com.drones.messages.DroneMessages";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.WelcomeMessage,
      com.drones.messages.DroneMessagesOuterClass.AnswerWelcome> getWelcomeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "welcome",
      requestType = com.drones.messages.DroneMessagesOuterClass.WelcomeMessage.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.AnswerWelcome.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.WelcomeMessage,
      com.drones.messages.DroneMessagesOuterClass.AnswerWelcome> getWelcomeMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.WelcomeMessage, com.drones.messages.DroneMessagesOuterClass.AnswerWelcome> getWelcomeMethod;
    if ((getWelcomeMethod = DroneMessagesGrpc.getWelcomeMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getWelcomeMethod = DroneMessagesGrpc.getWelcomeMethod) == null) {
          DroneMessagesGrpc.getWelcomeMethod = getWelcomeMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.WelcomeMessage, com.drones.messages.DroneMessagesOuterClass.AnswerWelcome>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "welcome"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.WelcomeMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.AnswerWelcome.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("welcome"))
              .build();
        }
      }
    }
    return getWelcomeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.OrderMessage,
      com.drones.messages.DroneMessagesOuterClass.OrderMessage> getAssignDeliveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "assignDelivery",
      requestType = com.drones.messages.DroneMessagesOuterClass.OrderMessage.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.OrderMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.OrderMessage,
      com.drones.messages.DroneMessagesOuterClass.OrderMessage> getAssignDeliveryMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.OrderMessage, com.drones.messages.DroneMessagesOuterClass.OrderMessage> getAssignDeliveryMethod;
    if ((getAssignDeliveryMethod = DroneMessagesGrpc.getAssignDeliveryMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getAssignDeliveryMethod = DroneMessagesGrpc.getAssignDeliveryMethod) == null) {
          DroneMessagesGrpc.getAssignDeliveryMethod = getAssignDeliveryMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.OrderMessage, com.drones.messages.DroneMessagesOuterClass.OrderMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "assignDelivery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.OrderMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.OrderMessage.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("assignDelivery"))
              .build();
        }
      }
    }
    return getAssignDeliveryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage,
      com.drones.messages.DroneMessagesOuterClass.StatOrderMessage> getGiveStatDeliveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "giveStatDelivery",
      requestType = com.drones.messages.DroneMessagesOuterClass.StatOrderMessage.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.StatOrderMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage,
      com.drones.messages.DroneMessagesOuterClass.StatOrderMessage> getGiveStatDeliveryMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage, com.drones.messages.DroneMessagesOuterClass.StatOrderMessage> getGiveStatDeliveryMethod;
    if ((getGiveStatDeliveryMethod = DroneMessagesGrpc.getGiveStatDeliveryMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getGiveStatDeliveryMethod = DroneMessagesGrpc.getGiveStatDeliveryMethod) == null) {
          DroneMessagesGrpc.getGiveStatDeliveryMethod = getGiveStatDeliveryMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage, com.drones.messages.DroneMessagesOuterClass.StatOrderMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "giveStatDelivery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.StatOrderMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.StatOrderMessage.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("giveStatDelivery"))
              .build();
        }
      }
    }
    return getGiveStatDeliveryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ElectionMessage,
      com.drones.messages.DroneMessagesOuterClass.Response> getSendElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendElection",
      requestType = com.drones.messages.DroneMessagesOuterClass.ElectionMessage.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ElectionMessage,
      com.drones.messages.DroneMessagesOuterClass.Response> getSendElectionMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ElectionMessage, com.drones.messages.DroneMessagesOuterClass.Response> getSendElectionMethod;
    if ((getSendElectionMethod = DroneMessagesGrpc.getSendElectionMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getSendElectionMethod = DroneMessagesGrpc.getSendElectionMethod) == null) {
          DroneMessagesGrpc.getSendElectionMethod = getSendElectionMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.ElectionMessage, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendElection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.ElectionMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("sendElection"))
              .build();
        }
      }
    }
    return getSendElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ElectedMessage,
      com.drones.messages.DroneMessagesOuterClass.Response> getSendElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendElected",
      requestType = com.drones.messages.DroneMessagesOuterClass.ElectedMessage.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ElectedMessage,
      com.drones.messages.DroneMessagesOuterClass.Response> getSendElectedMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ElectedMessage, com.drones.messages.DroneMessagesOuterClass.Response> getSendElectedMethod;
    if ((getSendElectedMethod = DroneMessagesGrpc.getSendElectedMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getSendElectedMethod = DroneMessagesGrpc.getSendElectedMethod) == null) {
          DroneMessagesGrpc.getSendElectedMethod = getSendElectedMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.ElectedMessage, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendElected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.ElectedMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("sendElected"))
              .build();
        }
      }
    }
    return getSendElectedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.AnswerElected,
      com.drones.messages.DroneMessagesOuterClass.Response> getSendInfoToNewMasterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendInfoToNewMaster",
      requestType = com.drones.messages.DroneMessagesOuterClass.AnswerElected.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.AnswerElected,
      com.drones.messages.DroneMessagesOuterClass.Response> getSendInfoToNewMasterMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.AnswerElected, com.drones.messages.DroneMessagesOuterClass.Response> getSendInfoToNewMasterMethod;
    if ((getSendInfoToNewMasterMethod = DroneMessagesGrpc.getSendInfoToNewMasterMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getSendInfoToNewMasterMethod = DroneMessagesGrpc.getSendInfoToNewMasterMethod) == null) {
          DroneMessagesGrpc.getSendInfoToNewMasterMethod = getSendInfoToNewMasterMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.AnswerElected, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendInfoToNewMaster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.AnswerElected.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("sendInfoToNewMaster"))
              .build();
        }
      }
    }
    return getSendInfoToNewMasterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.Response,
      com.drones.messages.DroneMessagesOuterClass.Response> getPingMasterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pingMaster",
      requestType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.Response,
      com.drones.messages.DroneMessagesOuterClass.Response> getPingMasterMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.Response, com.drones.messages.DroneMessagesOuterClass.Response> getPingMasterMethod;
    if ((getPingMasterMethod = DroneMessagesGrpc.getPingMasterMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getPingMasterMethod = DroneMessagesGrpc.getPingMasterMethod) == null) {
          DroneMessagesGrpc.getPingMasterMethod = getPingMasterMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.Response, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pingMaster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("pingMaster"))
              .build();
        }
      }
    }
    return getPingMasterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ChargeRequest,
      com.drones.messages.DroneMessagesOuterClass.Response> getChargeRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "chargeRequest",
      requestType = com.drones.messages.DroneMessagesOuterClass.ChargeRequest.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ChargeRequest,
      com.drones.messages.DroneMessagesOuterClass.Response> getChargeRequestMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.ChargeRequest, com.drones.messages.DroneMessagesOuterClass.Response> getChargeRequestMethod;
    if ((getChargeRequestMethod = DroneMessagesGrpc.getChargeRequestMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getChargeRequestMethod = DroneMessagesGrpc.getChargeRequestMethod) == null) {
          DroneMessagesGrpc.getChargeRequestMethod = getChargeRequestMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.ChargeRequest, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "chargeRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.ChargeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("chargeRequest"))
              .build();
        }
      }
    }
    return getChargeRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.IdMessage,
      com.drones.messages.DroneMessagesOuterClass.Response> getInfoMasterRechargingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "infoMasterRecharging",
      requestType = com.drones.messages.DroneMessagesOuterClass.IdMessage.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.IdMessage,
      com.drones.messages.DroneMessagesOuterClass.Response> getInfoMasterRechargingMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.IdMessage, com.drones.messages.DroneMessagesOuterClass.Response> getInfoMasterRechargingMethod;
    if ((getInfoMasterRechargingMethod = DroneMessagesGrpc.getInfoMasterRechargingMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getInfoMasterRechargingMethod = DroneMessagesGrpc.getInfoMasterRechargingMethod) == null) {
          DroneMessagesGrpc.getInfoMasterRechargingMethod = getInfoMasterRechargingMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.IdMessage, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "infoMasterRecharging"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.IdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("infoMasterRecharging"))
              .build();
        }
      }
    }
    return getInfoMasterRechargingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.EndedCharge,
      com.drones.messages.DroneMessagesOuterClass.Response> getInfoEndedChargeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "infoEndedCharge",
      requestType = com.drones.messages.DroneMessagesOuterClass.EndedCharge.class,
      responseType = com.drones.messages.DroneMessagesOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.EndedCharge,
      com.drones.messages.DroneMessagesOuterClass.Response> getInfoEndedChargeMethod() {
    io.grpc.MethodDescriptor<com.drones.messages.DroneMessagesOuterClass.EndedCharge, com.drones.messages.DroneMessagesOuterClass.Response> getInfoEndedChargeMethod;
    if ((getInfoEndedChargeMethod = DroneMessagesGrpc.getInfoEndedChargeMethod) == null) {
      synchronized (DroneMessagesGrpc.class) {
        if ((getInfoEndedChargeMethod = DroneMessagesGrpc.getInfoEndedChargeMethod) == null) {
          DroneMessagesGrpc.getInfoEndedChargeMethod = getInfoEndedChargeMethod =
              io.grpc.MethodDescriptor.<com.drones.messages.DroneMessagesOuterClass.EndedCharge, com.drones.messages.DroneMessagesOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "infoEndedCharge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.EndedCharge.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.drones.messages.DroneMessagesOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DroneMessagesMethodDescriptorSupplier("infoEndedCharge"))
              .build();
        }
      }
    }
    return getInfoEndedChargeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DroneMessagesStub newStub(io.grpc.Channel channel) {
    return new DroneMessagesStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DroneMessagesBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DroneMessagesBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DroneMessagesFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DroneMessagesFutureStub(channel);
  }

  /**
   */
  public static abstract class DroneMessagesImplBase implements io.grpc.BindableService {

    /**
     */
    public void welcome(com.drones.messages.DroneMessagesOuterClass.WelcomeMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.AnswerWelcome> responseObserver) {
      asyncUnimplementedUnaryCall(getWelcomeMethod(), responseObserver);
    }

    /**
     */
    public void assignDelivery(com.drones.messages.DroneMessagesOuterClass.OrderMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.OrderMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getAssignDeliveryMethod(), responseObserver);
    }

    /**
     */
    public void giveStatDelivery(com.drones.messages.DroneMessagesOuterClass.StatOrderMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGiveStatDeliveryMethod(), responseObserver);
    }

    /**
     */
    public void sendElection(com.drones.messages.DroneMessagesOuterClass.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getSendElectionMethod(), responseObserver);
    }

    /**
     */
    public void sendElected(com.drones.messages.DroneMessagesOuterClass.ElectedMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getSendElectedMethod(), responseObserver);
    }

    /**
     */
    public void sendInfoToNewMaster(com.drones.messages.DroneMessagesOuterClass.AnswerElected request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getSendInfoToNewMasterMethod(), responseObserver);
    }

    /**
     */
    public void pingMaster(com.drones.messages.DroneMessagesOuterClass.Response request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMasterMethod(), responseObserver);
    }

    /**
     */
    public void chargeRequest(com.drones.messages.DroneMessagesOuterClass.ChargeRequest request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getChargeRequestMethod(), responseObserver);
    }

    /**
     */
    public void infoMasterRecharging(com.drones.messages.DroneMessagesOuterClass.IdMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getInfoMasterRechargingMethod(), responseObserver);
    }

    /**
     */
    public void infoEndedCharge(com.drones.messages.DroneMessagesOuterClass.EndedCharge request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getInfoEndedChargeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getWelcomeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.WelcomeMessage,
                com.drones.messages.DroneMessagesOuterClass.AnswerWelcome>(
                  this, METHODID_WELCOME)))
          .addMethod(
            getAssignDeliveryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.OrderMessage,
                com.drones.messages.DroneMessagesOuterClass.OrderMessage>(
                  this, METHODID_ASSIGN_DELIVERY)))
          .addMethod(
            getGiveStatDeliveryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.StatOrderMessage,
                com.drones.messages.DroneMessagesOuterClass.StatOrderMessage>(
                  this, METHODID_GIVE_STAT_DELIVERY)))
          .addMethod(
            getSendElectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.ElectionMessage,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_SEND_ELECTION)))
          .addMethod(
            getSendElectedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.ElectedMessage,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_SEND_ELECTED)))
          .addMethod(
            getSendInfoToNewMasterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.AnswerElected,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_SEND_INFO_TO_NEW_MASTER)))
          .addMethod(
            getPingMasterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.Response,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_PING_MASTER)))
          .addMethod(
            getChargeRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.ChargeRequest,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_CHARGE_REQUEST)))
          .addMethod(
            getInfoMasterRechargingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.IdMessage,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_INFO_MASTER_RECHARGING)))
          .addMethod(
            getInfoEndedChargeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drones.messages.DroneMessagesOuterClass.EndedCharge,
                com.drones.messages.DroneMessagesOuterClass.Response>(
                  this, METHODID_INFO_ENDED_CHARGE)))
          .build();
    }
  }

  /**
   */
  public static final class DroneMessagesStub extends io.grpc.stub.AbstractStub<DroneMessagesStub> {
    private DroneMessagesStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneMessagesStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneMessagesStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneMessagesStub(channel, callOptions);
    }

    /**
     */
    public void welcome(com.drones.messages.DroneMessagesOuterClass.WelcomeMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.AnswerWelcome> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWelcomeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void assignDelivery(com.drones.messages.DroneMessagesOuterClass.OrderMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.OrderMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssignDeliveryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void giveStatDelivery(com.drones.messages.DroneMessagesOuterClass.StatOrderMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGiveStatDeliveryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendElection(com.drones.messages.DroneMessagesOuterClass.ElectionMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendElected(com.drones.messages.DroneMessagesOuterClass.ElectedMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendElectedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendInfoToNewMaster(com.drones.messages.DroneMessagesOuterClass.AnswerElected request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendInfoToNewMasterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pingMaster(com.drones.messages.DroneMessagesOuterClass.Response request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMasterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void chargeRequest(com.drones.messages.DroneMessagesOuterClass.ChargeRequest request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChargeRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void infoMasterRecharging(com.drones.messages.DroneMessagesOuterClass.IdMessage request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInfoMasterRechargingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void infoEndedCharge(com.drones.messages.DroneMessagesOuterClass.EndedCharge request,
        io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInfoEndedChargeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DroneMessagesBlockingStub extends io.grpc.stub.AbstractStub<DroneMessagesBlockingStub> {
    private DroneMessagesBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneMessagesBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneMessagesBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneMessagesBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.AnswerWelcome welcome(com.drones.messages.DroneMessagesOuterClass.WelcomeMessage request) {
      return blockingUnaryCall(
          getChannel(), getWelcomeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.OrderMessage assignDelivery(com.drones.messages.DroneMessagesOuterClass.OrderMessage request) {
      return blockingUnaryCall(
          getChannel(), getAssignDeliveryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.StatOrderMessage giveStatDelivery(com.drones.messages.DroneMessagesOuterClass.StatOrderMessage request) {
      return blockingUnaryCall(
          getChannel(), getGiveStatDeliveryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response sendElection(com.drones.messages.DroneMessagesOuterClass.ElectionMessage request) {
      return blockingUnaryCall(
          getChannel(), getSendElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response sendElected(com.drones.messages.DroneMessagesOuterClass.ElectedMessage request) {
      return blockingUnaryCall(
          getChannel(), getSendElectedMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response sendInfoToNewMaster(com.drones.messages.DroneMessagesOuterClass.AnswerElected request) {
      return blockingUnaryCall(
          getChannel(), getSendInfoToNewMasterMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response pingMaster(com.drones.messages.DroneMessagesOuterClass.Response request) {
      return blockingUnaryCall(
          getChannel(), getPingMasterMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response chargeRequest(com.drones.messages.DroneMessagesOuterClass.ChargeRequest request) {
      return blockingUnaryCall(
          getChannel(), getChargeRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response infoMasterRecharging(com.drones.messages.DroneMessagesOuterClass.IdMessage request) {
      return blockingUnaryCall(
          getChannel(), getInfoMasterRechargingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drones.messages.DroneMessagesOuterClass.Response infoEndedCharge(com.drones.messages.DroneMessagesOuterClass.EndedCharge request) {
      return blockingUnaryCall(
          getChannel(), getInfoEndedChargeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DroneMessagesFutureStub extends io.grpc.stub.AbstractStub<DroneMessagesFutureStub> {
    private DroneMessagesFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DroneMessagesFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DroneMessagesFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DroneMessagesFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.AnswerWelcome> welcome(
        com.drones.messages.DroneMessagesOuterClass.WelcomeMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getWelcomeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.OrderMessage> assignDelivery(
        com.drones.messages.DroneMessagesOuterClass.OrderMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getAssignDeliveryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage> giveStatDelivery(
        com.drones.messages.DroneMessagesOuterClass.StatOrderMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGiveStatDeliveryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> sendElection(
        com.drones.messages.DroneMessagesOuterClass.ElectionMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSendElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> sendElected(
        com.drones.messages.DroneMessagesOuterClass.ElectedMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSendElectedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> sendInfoToNewMaster(
        com.drones.messages.DroneMessagesOuterClass.AnswerElected request) {
      return futureUnaryCall(
          getChannel().newCall(getSendInfoToNewMasterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> pingMaster(
        com.drones.messages.DroneMessagesOuterClass.Response request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMasterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> chargeRequest(
        com.drones.messages.DroneMessagesOuterClass.ChargeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChargeRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> infoMasterRecharging(
        com.drones.messages.DroneMessagesOuterClass.IdMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getInfoMasterRechargingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drones.messages.DroneMessagesOuterClass.Response> infoEndedCharge(
        com.drones.messages.DroneMessagesOuterClass.EndedCharge request) {
      return futureUnaryCall(
          getChannel().newCall(getInfoEndedChargeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_WELCOME = 0;
  private static final int METHODID_ASSIGN_DELIVERY = 1;
  private static final int METHODID_GIVE_STAT_DELIVERY = 2;
  private static final int METHODID_SEND_ELECTION = 3;
  private static final int METHODID_SEND_ELECTED = 4;
  private static final int METHODID_SEND_INFO_TO_NEW_MASTER = 5;
  private static final int METHODID_PING_MASTER = 6;
  private static final int METHODID_CHARGE_REQUEST = 7;
  private static final int METHODID_INFO_MASTER_RECHARGING = 8;
  private static final int METHODID_INFO_ENDED_CHARGE = 9;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DroneMessagesImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DroneMessagesImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_WELCOME:
          serviceImpl.welcome((com.drones.messages.DroneMessagesOuterClass.WelcomeMessage) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.AnswerWelcome>) responseObserver);
          break;
        case METHODID_ASSIGN_DELIVERY:
          serviceImpl.assignDelivery((com.drones.messages.DroneMessagesOuterClass.OrderMessage) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.OrderMessage>) responseObserver);
          break;
        case METHODID_GIVE_STAT_DELIVERY:
          serviceImpl.giveStatDelivery((com.drones.messages.DroneMessagesOuterClass.StatOrderMessage) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.StatOrderMessage>) responseObserver);
          break;
        case METHODID_SEND_ELECTION:
          serviceImpl.sendElection((com.drones.messages.DroneMessagesOuterClass.ElectionMessage) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        case METHODID_SEND_ELECTED:
          serviceImpl.sendElected((com.drones.messages.DroneMessagesOuterClass.ElectedMessage) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        case METHODID_SEND_INFO_TO_NEW_MASTER:
          serviceImpl.sendInfoToNewMaster((com.drones.messages.DroneMessagesOuterClass.AnswerElected) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        case METHODID_PING_MASTER:
          serviceImpl.pingMaster((com.drones.messages.DroneMessagesOuterClass.Response) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        case METHODID_CHARGE_REQUEST:
          serviceImpl.chargeRequest((com.drones.messages.DroneMessagesOuterClass.ChargeRequest) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        case METHODID_INFO_MASTER_RECHARGING:
          serviceImpl.infoMasterRecharging((com.drones.messages.DroneMessagesOuterClass.IdMessage) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        case METHODID_INFO_ENDED_CHARGE:
          serviceImpl.infoEndedCharge((com.drones.messages.DroneMessagesOuterClass.EndedCharge) request,
              (io.grpc.stub.StreamObserver<com.drones.messages.DroneMessagesOuterClass.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DroneMessagesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DroneMessagesBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.drones.messages.DroneMessagesOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DroneMessages");
    }
  }

  private static final class DroneMessagesFileDescriptorSupplier
      extends DroneMessagesBaseDescriptorSupplier {
    DroneMessagesFileDescriptorSupplier() {}
  }

  private static final class DroneMessagesMethodDescriptorSupplier
      extends DroneMessagesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DroneMessagesMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DroneMessagesGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DroneMessagesFileDescriptorSupplier())
              .addMethod(getWelcomeMethod())
              .addMethod(getAssignDeliveryMethod())
              .addMethod(getGiveStatDeliveryMethod())
              .addMethod(getSendElectionMethod())
              .addMethod(getSendElectedMethod())
              .addMethod(getSendInfoToNewMasterMethod())
              .addMethod(getPingMasterMethod())
              .addMethod(getChargeRequestMethod())
              .addMethod(getInfoMasterRechargingMethod())
              .addMethod(getInfoEndedChargeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
