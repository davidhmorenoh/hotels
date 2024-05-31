package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserService userService;

    public UserDto execute(Long id) {
        return userService.getUserById(id);
    }

}