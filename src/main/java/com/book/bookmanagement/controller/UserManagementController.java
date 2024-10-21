package com.book.bookmanagement.controller;

import com.book.bookmanagement.dto.AuthResponseDto;
import com.book.bookmanagement.dto.LoginDto;
import com.book.bookmanagement.dto.RegisterDto;
import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.exception.RegistrationException;
import com.book.bookmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserManagementController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDto user) throws RegistrationException {

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setAddress(user.getAddress());
        newUser.setPhoneNumber(user.getPhoneNumber());


        User savedUser = userService.register(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUserProfile(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateProfile(user));
    }
}
