package com.book.bookmanagement.service.impl;

import com.book.bookmanagement.entity.Book;
import com.book.bookmanagement.entity.BorrowTransaction;
import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.exception.ResourceNotFoundException;
import com.book.bookmanagement.repository.BookRepository;
import com.book.bookmanagement.repository.BorrowTransactionRepository;
import com.book.bookmanagement.repository.UserRepository;
import com.book.bookmanagement.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowTransactionRepository borrowTransactionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    public BorrowTransaction borrowBook(Long userId, Long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isEmpty() || bookOpt.get().getAvailableCopies() <= 0) {
            throw new ResourceNotFoundException("Book not available");
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

        BorrowTransaction transaction = new BorrowTransaction();
        transaction.setBook(book);
        transaction.setUser(user);
        transaction.setBorrowDate(LocalDateTime.now());
        transaction.setDueDate(LocalDateTime.now().plusWeeks(2)); // Set a 2-week due date
        transaction.setStatus("borrowed");

        return borrowTransactionRepository.save(transaction);
    }

    public BorrowTransaction returnBook(Long userId, Long transactionId) {
        Optional<BorrowTransaction> transactionOpt = borrowTransactionRepository.findById(transactionId);
        if (transactionOpt.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }

        BorrowTransaction transaction = transactionOpt.get();
        if (!transaction.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }

        transaction.setReturnDate(LocalDateTime.now());
        transaction.setStatus("returned");

        Book book = transaction.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return borrowTransactionRepository.save(transaction);
    }

    public BorrowTransaction renewBook(Long userId, Long transactionId) {
        Optional<BorrowTransaction> transactionOpt = borrowTransactionRepository.findById(transactionId);
        if (transactionOpt.isEmpty() ) {
            throw new ResourceNotFoundException("Transaction not found");
        }

        BorrowTransaction transaction = transactionOpt.get();
        if (!transaction.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }

        if(transaction.getStatus().equals("returned")) {
            throw new RuntimeException("Book already returned");
        }

        if (transaction.getRenewedCount() >= 2) {
            throw new RuntimeException("Renew limit reached");
        }

        transaction.setDueDate(transaction.getDueDate().plusWeeks(2)); // Extend due date by 2 weeks
        transaction.setRenewedCount(transaction.getRenewedCount() + 1);

        return borrowTransactionRepository.save(transaction);
    }
}
