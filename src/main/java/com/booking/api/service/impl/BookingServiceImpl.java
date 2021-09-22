package com.booking.api.service.impl;

import com.booking.api.controller.data.request.BookCreationRequest;
import com.booking.api.domain.Book;
import com.booking.api.domain.User;
import com.booking.api.domain.exception.BusinessException;
import com.booking.api.mapper.BookingMapper;
import com.booking.api.repository.BookRepository;
import com.booking.api.service.BookingService;
import com.booking.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookRepository bookRepository;
    private final UserService userService;

    @Override
    public void book(BookCreationRequest bookCreationRequest) {
        User user = Optional.ofNullable(userService.findById(bookCreationRequest.getUserId()))
                .orElseThrow(() -> new BusinessException(String.format("User [%s] not found", bookCreationRequest.getUserId())));
        Book book = BookingMapper.toDomain(bookCreationRequest, user);
        validateDates(book.getDateFrom(), book.getDateTo());
        checkRoomAvailability();
        bookRepository.save(book);
    }

    private void checkRoomAvailability() {
        // TODO
        // bookRepository.
    }

    private void validateDates(LocalDate dateFrom, LocalDate dateTo) {
        boolean isDateToBeforeDateFrom = dateTo.isBefore(dateFrom);
        boolean isDateFromBeforeNow = dateFrom.isBefore(LocalDate.now());
        boolean isDateToBeforeNow = dateTo.isBefore(LocalDate.now());

        if (isDateToBeforeDateFrom) {
            throw new BusinessException(String.format("dateTo [%s] cannot be before dateFrom [%s]", dateTo, dateFrom));
        } else if (isDateFromBeforeNow) {
            throw new BusinessException(String.format("dateFrom [%s] cannot be before now ", dateFrom));
        } else if (isDateToBeforeNow) {
            throw new BusinessException(String.format("dateTo [%s] cannot be before now", dateTo));
        }
    }
}
