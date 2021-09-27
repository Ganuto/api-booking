package com.booking.api.controller;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.controller.data.request.BookUpdateRequest;
import com.booking.api.domain.Book;
import com.booking.api.mock.BookMock;
import com.booking.api.service.BookingService;
import com.booking.api.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    public void createBookSuccessfully() throws Exception {
        BookCreationRequest bookCreationRequest = BookMock.createBookCreationRequest();

        doNothing().when(bookingService).book(any(BookCreationRequest.class));

        mockMvc.perform(post("/booking/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonString(bookCreationRequest)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getBookSuccessfully() throws Exception {
        Book book = BookMock.createBook();
        List<Book> bookList = Collections.singletonList(book);
        LocalDate localDateFrom = book.getDateFrom();
        LocalDate localDateTo = book.getDateTo();

        when(bookingService.find(localDateFrom, localDateTo)).thenReturn(bookList);

        mockMvc.perform(get("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateFrom", "2021-09-26")
                        .param("dateTo", "2021-09-29"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(book.getId()))
                .andExpect(jsonPath("$.[0].user.id").value(book.getUser().getId()))
                .andExpect(jsonPath("$.[0].user.name").value(book.getUser().getName()))
                .andExpect(jsonPath("$.[0].user.document").value(book.getUser().getDocument()))
                .andExpect(jsonPath("$.[0].user.login").value(book.getUser().getLogin()))
                .andExpect(jsonPath("$.[0].user.password").value(book.getUser().getPassword()))
                .andExpect(jsonPath("$.[0].dateFrom").isNotEmpty())
                .andExpect(jsonPath("$.[0].dateTo").isNotEmpty())
                .andExpect(jsonPath("$.[0].createdDate").isNotEmpty());
    }

    @Test
    public void cancelBookSuccessfully() throws Exception {
        Book book = BookMock.createBook();

        doNothing().when(bookingService).delete(book.getId());

        mockMvc.perform(delete("/booking/book/:id".replace(":id", book.getId().toString()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateBookSuccessfully() throws Exception {
        Book book = BookMock.createBook();
        BookUpdateRequest bookUpdateRequest = BookMock.createBookUpdateRequest();

        doNothing().when(bookingService).update(book.getId(), bookUpdateRequest);

        mockMvc.perform(patch("/booking/book/1".replace(":id", book.getId().toString()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonString(bookUpdateRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
