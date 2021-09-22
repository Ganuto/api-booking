package com.booking.api.service;

import com.booking.api.controller.data.request.BookCreationRequest;

public interface BookingService {

    void book(BookCreationRequest bookCreationRequest);
}
