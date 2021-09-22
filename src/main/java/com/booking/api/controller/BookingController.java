package com.booking.api.controller;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.service.BookingService;
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
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Void> create(@RequestBody @Valid BookCreationRequest bookCreationRequest) {
        bookingService.book(bookCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
