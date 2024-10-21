package com.book.bookmanagement.controller;

import com.book.bookmanagement.entity.BorrowTransaction;
import com.book.bookmanagement.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<BorrowTransaction> borrowBook(@RequestParam Long userId, @PathVariable Long bookId) {
        BorrowTransaction transaction = borrowingService.borrowBook(userId, bookId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/return/{transactionId}")
    public ResponseEntity<BorrowTransaction> returnBook(@RequestParam Long userId, @PathVariable Long transactionId) {
        BorrowTransaction transaction = borrowingService.returnBook(userId, transactionId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/renew/{transactionId}")
    public ResponseEntity<BorrowTransaction> renewBook(@RequestParam Long userId, @PathVariable Long transactionId) {
        BorrowTransaction transaction = borrowingService.renewBook(userId, transactionId);
        return ResponseEntity.ok(transaction);
    }
}
