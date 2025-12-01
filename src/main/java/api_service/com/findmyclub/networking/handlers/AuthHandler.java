package api_service.com.findmyclub.networking.handlers;

import api_service.com.findmyclub.Grpc.Sep3;
import api_service.com.findmyclub.Grpc.Sep3.ActionTypeProto;
import auth_service.com.findmyclub.DTO.LoginRequest;
import auth_service.com.findmyclub.DTO.RegisterRequest;
import auth_service.com.findmyclub.model.User;
import auth_service.com.findmyclub.service.AuthService;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.StringValue;

public class AuthHandler implements FindMyClubHandler {

    private final AuthService authService;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Message handle(ActionTypeProto actionType, Object payload) {
        // payload with  FindMyClubMainHandler always  Any,
        // but interface was done with Object, so we cast.
        Any anyPayload = (Any) payload;

        try {
            return switch (actionType) {
                case ACTION_LOGIN -> handleLogin(anyPayload);
                case ACTION_REGISTER -> handleRegister(anyPayload);
                default -> throw new IllegalArgumentException("Unsupported auth action: " + actionType);
            };
        } catch (InvalidProtocolBufferException e)
        {
            throw new RuntimeException("Invalid auth payload", e);
        }
    }

    private Message handleLogin(Any payload) throws InvalidProtocolBufferException {
        Sep3.LoginRequestProto loginRequestProto = payload.unpack(Sep3.LoginRequestProto.class);

        LoginRequest dto = new LoginRequest()
        {
            @Override
            public String getEmail()
            {
                return loginRequestProto.getEmail();
            }
                                                    //didn't want to change dto's constructors so overriding here
            @Override
            public String getPassword()
            {
                return loginRequestProto.getPassword();
            }
        };

        User user = authService.login(dto);

        return Sep3.UserProto.newBuilder()
                .setId(user.getId().intValue())    // id in proto = int32
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .build();
    }

    private Message handleRegister(Any payload) throws InvalidProtocolBufferException {
        Sep3.RegisterRequestProto registerRequestProto = payload.unpack(Sep3.RegisterRequestProto.class);

        RegisterRequest dto = new RegisterRequest() {

            @Override
            public String getUsername() {
                return registerRequestProto.getUsername();
            }
            @Override
            public String getEmail() {
                return registerRequestProto.getEmail();         //same here, didn't change dtos just overriding here
            }

            @Override
            public String getPassword() {
                return registerRequestProto.getPassword();
            }
        };
        authService.register(dto);

        // if everything is successful
        return StringValue.of("Registered");
    }
}
