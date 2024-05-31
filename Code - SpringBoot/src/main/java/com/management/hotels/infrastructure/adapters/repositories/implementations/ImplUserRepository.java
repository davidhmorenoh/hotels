package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.UserNotFoundException;
import com.management.hotels.domain.ports.repositories.UserRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ImplUserRepository implements UserRepository {

    private final UserJpa userJpa;

    @Override
    public User findByUsername(String username) {
        return Optional.ofNullable(userJpa.findByUsername(username)).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public User findByEmail(String email) {
        return userJpa.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpa.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userJpa.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userJpa.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> findAll() {
        return userJpa.findAll();
    }

}