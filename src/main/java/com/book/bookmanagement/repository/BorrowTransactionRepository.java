package com.book.bookmanagement.repository;

import com.book.bookmanagement.entity.Book;
import com.book.bookmanagement.entity.BorrowTransaction;
import com.book.bookmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {
    List<BorrowTransaction> findByUser(User user);
    List<BorrowTransaction> findByBook(Book book);
}
