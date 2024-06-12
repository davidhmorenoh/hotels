package com.management.hotels.application.ports.services;

public interface RevokedTokenApplicationPortService {

    void revokeToken(String authorizationHeader);

    boolean isTokenRevoked(String token);

}