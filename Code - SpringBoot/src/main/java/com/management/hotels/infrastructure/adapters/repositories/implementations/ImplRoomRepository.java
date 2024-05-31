package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.exceptions.RoomNotFoundException;
import com.management.hotels.infrastructure.adapters.repositories.jpas.RoomJpa;
import com.management.hotels.domain.ports.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplRoomRepository implements RoomRepository {

    private final RoomJpa roomJpa;

    @Override
    public List<Room> findByHotelAndStatus(Hotel hotel, Status status) {
        return roomJpa.findByHotelAndStatus(hotel, status);
    }

    @Override
    public List<Room> findByHotel_HotelId(Long hotelId) {
        return roomJpa.findByHotel_HotelId(hotelId);
    }

    @Override
    public Room save(Room room) {
        return roomJpa.save(room);
    }

    @Override
    public void delete(Room room) {
        roomJpa.delete(room);
    }

    @Override
    public Room findById(Long id) {
        return roomJpa.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));
    }

    @Override
    public List<Room> findAll() {
        return roomJpa.findAll();
    }

}