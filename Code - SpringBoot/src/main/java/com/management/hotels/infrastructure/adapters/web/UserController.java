package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.application.usecases.users.GetAllUsersUseCase;
import com.management.hotels.application.usecases.users.LoginUserUseCase;
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
    private final LoginUserUseCase loginUserUseCase;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(registerUserUseCase.execute(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(loginUserUseCase.execute(username, password));
    }

}