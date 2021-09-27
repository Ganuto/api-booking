package com.booking.api.controller.data.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreationRequest {

    @NotBlank(message = "User's name cannot be empty")
    private String name;

    @NotBlank(message = "User's document cannot be empty")
    private String document;

    @NotBlank(message = "User's login cannot be empty")
    private String login;

    @NotNull
    private String password;
}
