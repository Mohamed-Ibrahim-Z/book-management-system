package com.book.bookmanagement.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String bookNotFound) {
        super(bookNotFound);
    }
}
