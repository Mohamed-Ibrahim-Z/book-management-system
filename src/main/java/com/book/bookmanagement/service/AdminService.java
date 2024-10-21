package com.book.bookmanagement.service;

import com.book.bookmanagement.entity.Book;
import com.book.bookmanagement.entity.User;

import java.util.List;

public interface AdminService {
    public List<User> getAllUsers();

    public void deleteUser(Long userId);

    public User updateUser(User user);
}
