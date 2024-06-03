package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.AuthenticationRequest;
import com.management.hotels.application.dtos.responses.AuthenticationResponse;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.InvalidPasswordException;
import com.management.hotels.domain.exceptions.UserNotFoundException;
import com.management.hotels.domain.ports.repositories.UserRepository;
import com.management.hotels.infrastructure.configuration.CustomUserDetailsService;
import com.management.hotels.infrastructure.configuration.JwtTokenConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtTokenConfig jwtTokenConfig;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtTokenConfig.generateToken(userDetails);
            return new AuthenticationResponse(jwt);
        }
        throw new InvalidPasswordException("Invalid password");
    }

}