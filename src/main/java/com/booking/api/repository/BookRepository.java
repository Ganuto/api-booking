package com.booking.api.repository;

import com.booking.api.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b " +
            "WHERE b.dateFrom >= :dateFrom")
    Book findAllByDateFrom(@Param("dateFrom") LocalDate dateFrom);

    @Query("SELECT b from Book b " +
            "WHERE (b.dateFrom BETWEEN :dateFrom AND :dateTo) " +
            "OR (b.dateTo BETWEEN :dateFrom AND :dateTo)")
    List<Book> findAllByDateFromAndDateTo(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);


    @Query("UPDATE Book b " +
            "SET b.dateFrom = :newDateFrom, dateTo = :newDateTo " +
            "WHERE b.id = :bookId")
    Book updateDateFromAndDateTo(@Param("bookId") Long bookId,
                                 @Param("newDateFrom") LocalDate newDateFrom,
                                 @Param("newDateTo") LocalDate newDateTo);
}
