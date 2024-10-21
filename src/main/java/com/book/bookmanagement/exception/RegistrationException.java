package com.book.bookmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RegistrationException extends RuntimeException {

    public RegistrationException(String message) {
        super(message);
    }
}
