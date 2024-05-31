package com.management.hotels.application.services;

import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.InvalidPasswordException;
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

    private final GenericMapper<UserDto, User> userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserDto getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id));
    }

    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return userMapper.toDto(user);
        }
        throw new InvalidPasswordException("Invalid password");
    }

}