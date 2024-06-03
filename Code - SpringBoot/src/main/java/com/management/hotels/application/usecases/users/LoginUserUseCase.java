package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.requests.AuthenticationRequest;
import com.management.hotels.application.dtos.responses.AuthenticationResponse;
import com.management.hotels.application.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {

    private final AuthenticationService authenticationService;

    public AuthenticationResponse execute(AuthenticationRequest authenticationRequest) {
        return authenticationService.loginUser(authenticationRequest);
    }

}