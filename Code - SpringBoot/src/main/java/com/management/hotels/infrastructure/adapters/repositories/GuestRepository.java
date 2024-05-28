package com.management.hotels.infrastructure.adapters.repositories;

import com.management.hotels.domain.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}