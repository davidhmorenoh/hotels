package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplUserMapper implements GenericMapper<UserDto, User> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}