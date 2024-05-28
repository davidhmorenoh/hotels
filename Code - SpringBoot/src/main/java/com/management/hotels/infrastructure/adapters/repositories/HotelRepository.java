package com.management.hotels.infrastructure.adapters.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCreatedBy(User user);

}