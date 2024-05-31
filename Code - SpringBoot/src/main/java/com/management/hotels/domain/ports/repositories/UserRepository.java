package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.User;

import java.util.List;

public interface UserRepository {

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();

}