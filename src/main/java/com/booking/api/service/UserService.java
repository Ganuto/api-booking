package com.booking.api.service;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;

public interface UserService {

    UserCreationResponse create(UserCreationRequest userCreationRequest);
}
