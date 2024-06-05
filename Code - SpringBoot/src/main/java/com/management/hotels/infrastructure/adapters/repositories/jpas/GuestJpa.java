package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestJpa extends JpaRepository<Guest, Long> {
}