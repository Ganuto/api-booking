package com.booking.api.service;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    void book(BookCreationRequest bookCreationRequest);

    List<Book> find(LocalDate from, LocalDate to);
}
