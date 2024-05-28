package com.management.hotels.application.controllers;

import com.management.hotels.application.dtos.UserDTO;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public User loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

}