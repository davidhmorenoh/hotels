package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserService userService;

    public UserDto execute(UserDto userDto) {
        return userService.registerUser(userDto);
    }

}