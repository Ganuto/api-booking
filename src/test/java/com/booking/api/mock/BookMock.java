package com.booking.api.mock;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.controller.data.request.BookUpdateRequest;
import com.booking.api.domain.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookMock {

    public static Book createBook() {
        Book book = new Book();

        book.setId(1L);
        book.setUser(UserMock.createUser());
        book.setCreatedDate(LocalDateTime.now());
        book.setDateFrom(LocalDate.of(2021, 9, 26));
        book.setDateTo(LocalDate.of(2021, 9, 29));

        return book;
    }

    public static BookCreationRequest createBookCreationRequest() {
        BookCreationRequest bookCreationRequest = new BookCreationRequest();

        bookCreationRequest.setUserId(UserMock.createUser().getId());
        bookCreationRequest.setDateFrom(LocalDate.now().plusDays(1));
        bookCreationRequest.setDateTo(LocalDate.now().plusDays(3));

        return bookCreationRequest;
    }

    public static BookUpdateRequest createBookUpdateRequest(){
        BookUpdateRequest bookUpdateRequest = new BookUpdateRequest();

        bookUpdateRequest.setDateFrom(LocalDate.now().plusDays(1));
        bookUpdateRequest.setDateTo(LocalDate.now().plusDays(3));

        return bookUpdateRequest;
    }
}
