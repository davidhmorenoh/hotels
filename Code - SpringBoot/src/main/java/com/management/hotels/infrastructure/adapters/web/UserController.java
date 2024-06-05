package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.requests.UserRequest;
import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.application.usecases.users.GetAllUsersUseCase;
import com.management.hotels.application.usecases.users.RegisterUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(registerUserUseCase.execute(userRequest));
    }

}