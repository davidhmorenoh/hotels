package com.management.hotels.application.ports.configuration;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenPortConfig {

    String generateToken(UserDetails userDetails);

    boolean validateToken(String token, UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenExpired(String token);

}