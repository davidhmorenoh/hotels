package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.UserDTO;
import com.management.hotels.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends GenericMapper<UserDTO, User> {

    public UserMapper(ModelMapper modelMapper) {
        super(modelMapper, UserDTO.class, User.class);
    }

}