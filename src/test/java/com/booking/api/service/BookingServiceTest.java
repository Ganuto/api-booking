package com.booking.api.service;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.controller.data.request.BookUpdateRequest;
import com.booking.api.domain.Book;
import com.booking.api.domain.User;
import com.booking.api.mock.BookMock;
import com.booking.api.mock.UserMock;
import com.booking.api.repository.BookRepository;
import com.booking.api.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookingServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private UserService userService;

    @SpyBean
    private BookingServiceImpl bookingService;

    @Test
    public void bookSuccessFully() {
        User user = UserMock.createUser();
        Book book = BookMock.createBook();
        BookCreationRequest bookCreationRequest = BookMock.createBookCreationRequest();

        when(userService.findById(anyLong())).thenReturn(user);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        bookingService.book(bookCreationRequest);

        verify(userService).findById(anyLong());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void findSuccessFully() {
        User user = UserMock.createUser();
        Book book = BookMock.createBook();
        BookCreationRequest bookCreationRequest = BookMock.createBookCreationRequest();

        when(bookRepository.findAllByDateFromAndDateTo(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.singletonList(book));

        List<Book> bookList = bookingService.find(book.getDateFrom(), book.getDateTo());

        verify(bookRepository).findAllByDateFromAndDateTo(any(LocalDate.class), any(LocalDate.class));
        assertEquals(bookList.get(0).getId(), book.getId());
        assertEquals(bookList.get(0).getUser(), book.getUser());
        assertEquals(bookList.get(0).getCreatedDate(), book.getCreatedDate());
        assertEquals(bookList.get(0).getDateFrom(), book.getDateFrom());
        assertEquals(bookList.get(0).getDateTo(), book.getDateTo());
    }

    @Test
    public void deleteSuccessFully() {
        Book book = BookMock.createBook();

        doNothing().when(bookRepository).deleteById(anyLong());

        bookingService.delete(book.getId());

        verify(bookRepository).deleteById(anyLong());
    }

    @Test
    public void updateSuccessFully() {
        BookUpdateRequest bookUpdateRequest = BookMock.createBookUpdateRequest();
        Book book = BookMock.createBook();

        when(bookRepository.updateDateFromAndDateTo(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(book);

        bookingService.update(book.getId(), bookUpdateRequest);

        verify(bookRepository).updateDateFromAndDateTo(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }
}
