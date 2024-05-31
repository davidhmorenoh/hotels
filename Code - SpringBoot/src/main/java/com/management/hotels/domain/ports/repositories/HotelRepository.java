package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;

import java.util.List;

public interface HotelRepository {

    List<Hotel> findByCreatedBy(User user);

    Hotel save(Hotel hotel);

    List<Hotel> findAll();

    Hotel findById(Long id);

    void delete(Hotel hotel);

}