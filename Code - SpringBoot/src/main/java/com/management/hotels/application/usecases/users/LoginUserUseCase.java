package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {

    private final UserService userService;

    public UserDto execute(String username, String password) {
        return userService.loginUser(username, password);
    }

}