package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelJpa extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCreatedBy(User user);

}