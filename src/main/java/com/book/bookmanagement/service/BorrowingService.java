package com.book.bookmanagement.service;

import com.book.bookmanagement.entity.BorrowTransaction;

public interface BorrowingService {
    public BorrowTransaction borrowBook(Long userId, Long bookId);

    public BorrowTransaction returnBook(Long userId, Long transactionId);

    public BorrowTransaction renewBook(Long userId, Long transactionId);




}
