package com.booking.api.controller.data.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookCreationResponse {

    private String message;
    private LocalDateTime localDateTime;
}
