package com.booking.api.repository;

import com.booking.api.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b " +
            "WHERE b.dateFrom >= :dateFrom")
    Book findAllByDateFrom(@Param("dateFrom") LocalDate dateFrom);
}
