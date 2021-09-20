package com.booking.api.controller.data.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationResponse {

    private Long userId;
    private String name;
    private String document;
    private String login;
}
