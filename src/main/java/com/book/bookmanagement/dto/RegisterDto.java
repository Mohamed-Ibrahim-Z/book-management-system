package com.book.bookmanagement.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegisterDto {

    // Not null validation
    @NonNull
    private String name;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String address;

    @NonNull
    private String phoneNumber;
}
