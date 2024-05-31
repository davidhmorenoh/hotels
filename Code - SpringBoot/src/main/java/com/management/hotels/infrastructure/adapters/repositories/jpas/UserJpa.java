package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

}