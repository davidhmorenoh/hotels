package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;

import java.util.Date;
import java.util.List;

public interface RoomRepository {

    List<Room> findAll();

    Room findById(Long id);

    List<Room> findByHotel(Hotel hotel);

    List<Room> findAvailableRooms(String city, Date checkInDate, Date checkOutDate, int capacity);

    Room save(Room room);

    void delete(Room room);

}