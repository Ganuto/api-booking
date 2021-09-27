package com.booking.api.mapper;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.domain.Book;
import com.booking.api.domain.User;

import java.time.LocalDateTime;

public abstract class BookingMapper {

    public static Book toDomain(BookCreationRequest bookCreationRequest, User user) {
        Book book = new Book();

        book.setUser(user);
        book.setDateFrom(bookCreationRequest.getDateFrom());
        book.setDateTo(bookCreationRequest.getDateTo());
        book.setCreatedDate(LocalDateTime.now());

        return book;
    }

}
