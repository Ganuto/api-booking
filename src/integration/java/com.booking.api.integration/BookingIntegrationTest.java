package com.booking.api.integration;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.domain.Book;
import com.booking.api.domain.User;
import com.booking.api.mock.BookMock;
import com.booking.api.mock.UserMock;
import com.booking.api.repository.BookRepository;
import com.booking.api.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class BookingIntegrationTest extends BookingApiApplicationIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        User user = UserMock.createUser();
        userRepository.save(user);
    }

    @Test
    public void bookSuccessfully() {
        BookCreationRequest bookCreationRequest = BookMock.createBookCreationRequest();

        IntegrationRequests.post("/booking/book", bookCreationRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        List<Book> bookList = bookRepository.findAllByDateFromAndDateTo(bookCreationRequest.getDateFrom(), bookCreationRequest.getDateTo());

        assertNotNull(bookList.get(0).getId());
        assertNotNull(bookList.get(0).getCreatedDate());
        assertNotNull(bookList.get(0).getUser().getId());
        assertNotNull(bookList.get(0).getUser().getDocument());
        assertNotNull(bookList.get(0).getUser().getLogin());
        assertNotNull(bookList.get(0).getUser().getName());
        assertEquals(bookCreationRequest.getUserId(), bookList.get(0).getUser().getId());
        assertEquals(bookCreationRequest.getDateFrom(), bookList.get(0).getDateFrom());
        assertEquals(bookCreationRequest.getDateTo(), bookList.get(0).getDateTo());
    }
}
