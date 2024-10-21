package com.book.bookmanagement.service.impl;

import com.book.bookmanagement.entity.Book;
import com.book.bookmanagement.entity.Reservation;
import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.exception.ResourceNotFoundException;
import com.book.bookmanagement.repository.BookRepository;
import com.book.bookmanagement.repository.ReservationRepository;
import com.book.bookmanagement.repository.UserRepository;
import com.book.bookmanagement.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Reservation reserveBook(Long userId, Long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isEmpty()) {
            throw new ResourceNotFoundException("Book not found");
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        Book book = bookOpt.get();
        User user = userOpt.get();

        if(book.getAvailableCopies() <= 0) {
            throw new ResourceNotFoundException("Book not available");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setReservedAt(LocalDateTime.now());
        reservation.setStatus("active");

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long userId, Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            throw new ResourceNotFoundException("Reservation not found");
        }

        Reservation reservation = reservationOpt.get();
        if (!reservation.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }

        if(reservation.getStatus().equals("cancelled")) {
            throw new RuntimeException("Reservation already cancelled");
        }


        Book book = reservation.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        reservation.setStatus("cancelled");
        reservationRepository.save(reservation);
    }
}
