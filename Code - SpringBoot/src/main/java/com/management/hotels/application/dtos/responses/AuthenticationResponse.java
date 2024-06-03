package com.management.hotels.application.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private String jwt;

}