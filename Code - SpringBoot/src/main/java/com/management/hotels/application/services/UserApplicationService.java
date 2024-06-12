package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.UserRequest;
import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.services.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserDomainService userDomainService;

    private final GenericMapper<UserRequest, UserResponse, User> userMapper;

    public List<UserResponse> getAllUsers() {
        return userDomainService.getAllUsers().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return userMapper.toDto(userDomainService.findById(id));
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User createdUser = userDomainService.createUser(user);
        return userMapper.toDto(createdUser);
    }

}