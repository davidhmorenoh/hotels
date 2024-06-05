package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.requests.UserRequest;
import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserService userService;

    public UserResponse execute(UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

}