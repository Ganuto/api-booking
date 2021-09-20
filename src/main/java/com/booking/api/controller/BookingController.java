package com.booking.api.controller;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    public ResponseEntity<?> create(@RequestBody @Valid BookCreationRequest bookCreationRequest){
        return null;
    }
}
