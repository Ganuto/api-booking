package com.booking.api.controller;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.controller.data.request.BookUpdateRequest;
import com.booking.api.domain.Book;
import com.booking.api.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Book>> get(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        return ResponseEntity.ok(bookingService.find(dateFrom, dateTo));
    }

    @PostMapping("/book")
    public ResponseEntity<Void> create(@RequestBody @Valid BookCreationRequest bookCreationRequest) {
        bookingService.book(bookCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/book/:id")
    public ResponseEntity<Void> cancel(@PathVariable Long bookId) {
        bookingService.delete(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/book/:id")
    public ResponseEntity<Void> update(@PathVariable Long bookId,
                                       @RequestBody @Valid BookUpdateRequest bookUpdateRequest) {
        bookingService.update(bookId, bookUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
