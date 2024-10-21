package com.book.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;

    private HttpStatus errorCode;

    private String errorMessage;

    private Date errorTime;
}
