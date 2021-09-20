package com.booking.api.mapper;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.domain.User;

public abstract class UserMapper {

    public static User toDomain(UserCreationRequest userCreationRequest) {
        User user = new User();

        user.setName(userCreationRequest.getName());
        user.setDocument(userCreationRequest.getDocument());
        user.setLogin(userCreationRequest.getLogin());
        user.setPassword(userCreationRequest.getPassword());

        return user;
    }

    public static UserCreationResponse toResponse(User user) {
        UserCreationResponse userCreationResponse = new UserCreationResponse();

        userCreationResponse.setUserId(user.getId());
        userCreationResponse.setName(user.getName());
        userCreationResponse.setDocument(user.getDocument());
        userCreationResponse.setLogin(user.getLogin());

        return userCreationResponse;
    }
}
