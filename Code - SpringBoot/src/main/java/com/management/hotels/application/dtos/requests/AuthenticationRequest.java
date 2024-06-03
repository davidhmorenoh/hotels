package com.management.hotels.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "User password is mandatory")
    @Size(min = 6, max = 100, message = "User password must be between 6 and 100 characters")
    private String password;

}