package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.requests.AuthenticationRequest;
import com.management.hotels.application.dtos.responses.AuthenticationResponse;
import com.management.hotels.application.services.RevokedTokenService;
import com.management.hotels.application.usecases.users.LoginUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginUserUseCase loginUserUseCase;
    private final RevokedTokenService revokedTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(loginUserUseCase.execute(authenticationRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String authorizationHeader) {
        revokedTokenService.revokeToken(authorizationHeader);
        return ResponseEntity.ok("Session completed");
    }

}