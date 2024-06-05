package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactJpa extends JpaRepository<EmergencyContact, Long> {
}