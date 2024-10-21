package com.book.bookmanagement.service.impl;

import com.book.bookmanagement.config.JwtTokenProvider;
import com.book.bookmanagement.dto.LoginDto;
import com.book.bookmanagement.entity.Role;
import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.exception.RegistrationException;
import com.book.bookmanagement.exception.InvalidTokenException;
import com.book.bookmanagement.repository.RoleRepository;
import com.book.bookmanagement.repository.UserRepository;
import com.book.bookmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RegistrationException("Username is already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RegistrationException("Email is already taken");
        }

        Role userRole = roleRepository.findByName("ROLE_USER");

        if(userRole == null) {
            userRole = roleRepository.save(new Role("ROLE_USER"));
        }

        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public String login(LoginDto loginDto) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key

        // 04 - Return the token to controller
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public User getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidTokenException("Invalid token, Please login again"));
    }

    @Override
    public User updateProfile(User user) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidTokenException("Invalid token, Please login again"));

        existingUser.setUsername(user.getUsername() == null ? existingUser.getUsername() : user.getUsername());
        existingUser.setEmail(user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        existingUser.setAddress(user.getAddress() == null ? existingUser.getAddress() : user.getAddress());
        existingUser.setPhoneNumber(user.getPhoneNumber() == null ? existingUser.getPhoneNumber() : user.getPhoneNumber());
        existingUser.setPassword(user.getPassword() == null ? existingUser.getPassword() : user.getPassword());

        return userRepository.save(existingUser);

    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
