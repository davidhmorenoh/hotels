package com.management.hotels.domain.ports.services;

import com.management.hotels.domain.entities.User;

public interface AuthenticationDomainPortService {

    User validateUserCredentials(String username, String password);

}