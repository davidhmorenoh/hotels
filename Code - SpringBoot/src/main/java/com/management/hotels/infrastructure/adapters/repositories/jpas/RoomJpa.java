package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomJpa extends JpaRepository<Room, Long> {

    List<Room> findByHotelAndStatus(Hotel hotel, Status status);

    List<Room> findByHotel(Hotel hotel);

}