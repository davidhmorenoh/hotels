package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.authentication.InvalidPasswordException;
import com.management.hotels.domain.exceptions.users.UserNotFoundException;
import com.management.hotels.domain.ports.repositories.UserRepository;
import com.management.hotels.domain.ports.services.AuthenticationDomainPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationDomainService implements AuthenticationDomainPortService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User validateUserCredentials(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return user;
    }

}