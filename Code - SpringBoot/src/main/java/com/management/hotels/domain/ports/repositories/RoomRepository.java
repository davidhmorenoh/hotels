package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;

import java.util.List;

public interface RoomRepository {

    List<Room> findByHotelAndStatus(Hotel hotel, Status status);

    List<Room> findByHotel_HotelId(Long hotelId);

    Room save(Room room);

    void delete(Room room);

    Room findById(Long id);

    List<Room> findAll();

}