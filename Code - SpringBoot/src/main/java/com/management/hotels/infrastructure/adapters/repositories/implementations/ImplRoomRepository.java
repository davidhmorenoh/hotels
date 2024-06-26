package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.exceptions.rooms.RoomNotFoundException;
import com.management.hotels.domain.ports.repositories.RoomRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.RoomJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplRoomRepository implements RoomRepository {

    private final RoomJpa roomJpa;

    @Override
    public List<Room> findAll() {
        return roomJpa.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomJpa.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));
    }

    @Override
    public List<Room> findByHotel(Hotel hotel) {
        return roomJpa.findByHotel(hotel);
    }

    @Override
    public List<Room> findAvailableRooms(String city, Date checkInDate, Date checkOutDate, int capacity) {
        return roomJpa.findAvailableRooms(city, checkInDate, checkOutDate, capacity);
    }

    @Override
    public Room save(Room room) {
        return roomJpa.save(room);
    }

    @Override
    public void delete(Room room) {
        roomJpa.delete(room);
    }

}