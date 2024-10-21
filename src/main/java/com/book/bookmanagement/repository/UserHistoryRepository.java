package com.book.bookmanagement.repository;

import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByUser(User user);
}
