package com.book.bookmanagement.service;

import com.book.bookmanagement.entity.Reservation;
import com.book.bookmanagement.repository.BookRepository;
import com.book.bookmanagement.repository.ReservationRepository;
import com.book.bookmanagement.repository.UserRepository;


public interface ReservationService {
    public Reservation reserveBook(Long userId, Long bookId);

    public void cancelReservation(Long userId, Long reservationId);
}
