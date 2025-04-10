package com.book.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class AuthResponseDto {
    private String accessToken;
}
