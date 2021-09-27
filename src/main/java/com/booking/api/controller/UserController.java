package com.booking.api.controller;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreationResponse> create(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userCreationRequest));
    }


}
