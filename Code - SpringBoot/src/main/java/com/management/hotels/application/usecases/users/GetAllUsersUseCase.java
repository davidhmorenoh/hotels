package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.application.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {

    private final UserApplicationService userApplicationService;

    public List<UserResponse> execute() {
        return userApplicationService.getAllUsers();
    }

}