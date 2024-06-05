package com.management.hotels.application.services;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RevokedTokenService {

    private final Set<String> revokedTokens = new HashSet<>();

    public void revokeToken(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        revokedTokens.add(token);
    }

    public boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }

}