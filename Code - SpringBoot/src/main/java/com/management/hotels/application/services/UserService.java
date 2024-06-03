package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.UserRequest;
import com.management.hotels.application.dtos.responses.UserResponse;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.UserAlreadyRegisteredException;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final GenericMapper<UserRequest, UserResponse, User> userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponse getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id));
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException("The user name is already in use.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyRegisteredException("Email is already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

}