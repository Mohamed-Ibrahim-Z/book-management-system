package com.book.bookmanagement.config;

import com.book.bookmanagement.dto.ErrorResponseDto;
import com.book.bookmanagement.exception.InvalidTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.UNAUTHORIZED,
                "Invalid token, Please login again",
                new Date()
        );


        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(errorResponseDto)
        );

    }
}
