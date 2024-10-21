package com.book.bookmanagement.service;

import com.book.bookmanagement.dto.LoginDto;
import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.exception.RegistrationException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User register(User user);

    String login(LoginDto loginDto);

    User getProfile();

    User updateProfile(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();
}
