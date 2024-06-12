package com.management.hotels.application.services;

import com.management.hotels.application.ports.services.RevokedTokenApplicationPortService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RevokedTokenApplicationService implements RevokedTokenApplicationPortService {

    private final Set<String> revokedTokens = new HashSet<>();

    @Override
    public void revokeToken(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        revokedTokens.add(token);
    }

    @Override
    public boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }

}