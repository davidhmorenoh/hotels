package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.HotelNotFoundException;
import com.management.hotels.domain.ports.repositories.HotelRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.HotelJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplHotelRepository implements HotelRepository {

    private final HotelJpa hotelJpa;

    @Override
    public List<Hotel> findByCreatedBy(User user) {
        return hotelJpa.findByCreatedBy(user);
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelJpa.save(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelJpa.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return hotelJpa.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));
    }

    @Override
    public void delete(Hotel hotel) {
        hotelJpa.delete(hotel);
    }

}