package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserService userService;

    public UserResponse execute(Long id) {
        return userService.getUserById(id);
    }

}