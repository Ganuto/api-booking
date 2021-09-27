package com.booking.api.service;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.domain.User;

public interface UserService {

    UserCreationResponse create(UserCreationRequest userCreationRequest);

    User findById(Long id);
}
