package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();

}