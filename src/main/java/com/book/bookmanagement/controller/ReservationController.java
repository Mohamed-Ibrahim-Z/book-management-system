package com.book.bookmanagement.controller;

import com.book.bookmanagement.entity.Reservation;
import com.book.bookmanagement.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reserve/{bookId}")
    public ResponseEntity<Reservation> reserveBook(@RequestParam Long userId, @PathVariable Long bookId) {
        Reservation reservation = reservationService.reserveBook(userId, bookId);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(@RequestParam Long userId, @PathVariable Long reservationId) {
        reservationService.cancelReservation(userId, reservationId);
        return ResponseEntity.ok("Reservation cancelled");
    }
}
