package com.management.hotels.application.usecases.users;

import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.application.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserApplicationService userApplicationService;

    public UserResponse execute(Long id) {
        return userApplicationService.getUserById(id);
    }

}