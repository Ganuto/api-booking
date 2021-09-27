package com.booking.api.mock;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.domain.User;

public class UserMock {

    public static UserCreationResponse createUserCreationResponse() {
        UserCreationResponse userCreationResponse = new UserCreationResponse();

        userCreationResponse.setUserId(123L);
        userCreationResponse.setName("Test");
        userCreationResponse.setDocument("12345678910");
        userCreationResponse.setLogin("testLogin");

        return userCreationResponse;
    }

    public static UserCreationRequest createUserCreationRequest() {
        UserCreationRequest userCreationRequest = new UserCreationRequest();

        userCreationRequest.setLogin("testLogin");
        userCreationRequest.setDocument("12345678910");
        userCreationRequest.setName("Test");
        userCreationRequest.setPassword("123456abc");

        return userCreationRequest;
    }

    public static User createUser() {
        User user = new User();

        user.setId(123L);
        user.setLogin("testLogin");
        user.setDocument("12345678910");
        user.setName("Test");
        user.setPassword("123456abc");

        return user;
    }
}
