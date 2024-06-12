package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.UserType;
import com.management.hotels.domain.exceptions.users.UserAlreadyRegisteredException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException("The user name is already in use.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyRegisteredException("Email is already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private User validateUserType(Long userId, UserType userType, String actors) {
        User user = this.findById(userId);
        if (user.getUserType() != userType) {
            throw new UserNotAuthorizedToPerformOperationException("Only " + actors + " can perform this operation.");
        }
        return user;
    }

    public User validateUserIsAgent(Long userId) {
        return this.validateUserType(userId, UserType.Agent, "agents");
    }

    public User validateUserIsTraveler(Long userId) {
        return this.validateUserType(userId, UserType.Traveler, "travelers");
    }

}