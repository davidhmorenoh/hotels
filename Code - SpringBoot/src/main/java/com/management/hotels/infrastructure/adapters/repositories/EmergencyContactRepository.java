package com.management.hotels.infrastructure.adapters.repositories;

import com.management.hotels.domain.entities.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}