package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.requests.AuthenticationRequest;
import com.management.hotels.application.dtos.responses.AuthenticationResponse;
import com.management.hotels.application.services.authentication.AuthenticationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {

    private final AuthenticationApplicationService authenticationApplicationService;

    public AuthenticationResponse execute(AuthenticationRequest authenticationRequest) {
        return authenticationApplicationService.loginUser(authenticationRequest);
    }

}