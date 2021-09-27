package com.booking.api.integration;

import com.booking.api.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BookingIntegrationTest {

    private static final String BASE_ENDPOINT_URL = "/booking";

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void bookSuccessfully(){
        
    }
}
