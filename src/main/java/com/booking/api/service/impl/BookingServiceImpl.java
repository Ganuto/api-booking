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
import java.time.Period;
import java.util.List;
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
        checkRoomAvailability(book.getDateFrom(), book.getDateTo());
        bookRepository.save(book);
    }

    public List<Book> find(LocalDate dateFrom, LocalDate dateTo) {
        return bookRepository.findAllByDateFromAndDateTo(dateFrom, dateTo);
    }

    private void checkRoomAvailability(LocalDate dateFrom, LocalDate dateTo) {
        List<Book> bookList = bookRepository.findAllByDateFromAndDateTo(dateFrom, dateTo);
        if (!bookList.isEmpty()) {
            throw new BusinessException(String.format("Room's already booked between the dates from [%s] and to [%s]", bookList.stream().findFirst().get().getDateFrom(), bookList.stream().findFirst().get().getDateTo()));
        }
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

        Period periodBetweenDateFromAndDateTo = Period.between(dateFrom, dateTo);
        if (periodBetweenDateFromAndDateTo.getYears() > 0) {
            throw new BusinessException("Booking permanence cannot be more than three days");
        } else if (periodBetweenDateFromAndDateTo.getMonths() > 0) {
            throw new BusinessException("Booking permanence cannot be more than three days");
        } else if (periodBetweenDateFromAndDateTo.getDays() > 3) {
            throw new BusinessException("Booking permanence cannot be more than three days");
        }

        Period periodBetweenDateFromAndNow = Period.between(LocalDate.now(), dateFrom);
        if (periodBetweenDateFromAndNow.getYears() > 0) {
            throw new BusinessException("Booking reservation cannot be more than 30 days in advance");
        } else if (periodBetweenDateFromAndNow.getMonths() > 0) {
            throw new BusinessException("Booking reservation cannot be more than 30 days in advance");
        } else if (periodBetweenDateFromAndNow.getDays() > 30) {
            throw new BusinessException("Booking reservation cannot be more than 30 days in advance");
        }

        boolean isBookingDayLessThanOneDayInAdvance = periodBetweenDateFromAndNow.getDays() <= 0;
        if (isBookingDayLessThanOneDayInAdvance) {
            throw new BusinessException("Booking reservation cannot be less than 1 day in advance");
        }
    }
}
