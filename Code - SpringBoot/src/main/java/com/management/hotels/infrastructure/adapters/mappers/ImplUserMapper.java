package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.requests.UserRequest;
import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplUserMapper implements GenericMapper<UserRequest, UserResponse, User> {

    private final ModelMapper modelMapper;

    @Override
    public UserResponse toDto(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public User toEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

}