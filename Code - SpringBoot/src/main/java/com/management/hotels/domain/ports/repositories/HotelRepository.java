package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;

import java.util.List;

public interface HotelRepository {

    List<Hotel> findAll();

    List<Hotel> findByUser(User user);

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    void delete(Hotel hotel);

}