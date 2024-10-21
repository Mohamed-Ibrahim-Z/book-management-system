package com.book.bookmanagement.repository;

import com.book.bookmanagement.entity.Book;
import com.book.bookmanagement.entity.Reservation;
import com.book.bookmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByBook(Book book);

}
