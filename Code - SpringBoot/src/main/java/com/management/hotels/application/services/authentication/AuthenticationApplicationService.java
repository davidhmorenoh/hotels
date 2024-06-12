package com.management.hotels.application.services.authentication;

import com.management.hotels.application.dtos.requests.AuthenticationRequest;
import com.management.hotels.application.dtos.responses.AuthenticationResponse;
import com.management.hotels.application.ports.configuration.JwtTokenPortConfig;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.ports.services.AuthenticationDomainPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationApplicationService {

    private final AuthenticationDomainPortService authenticationDomainPortService;
    private final JwtTokenPortConfig jwtTokenPortConfig;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) {
        User user = authenticationDomainPortService.validateUserCredentials(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwt = jwtTokenPortConfig.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

}