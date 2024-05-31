package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {

    private final UserService userService;

    public List<UserDto> execute() {
        return userService.getAllUsers();
    }

}