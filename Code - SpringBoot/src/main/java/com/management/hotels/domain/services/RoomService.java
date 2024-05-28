package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.infrastructure.adapters.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    //private final RoomMapper roomMapper;

    public Room createRoom(Room roomDTO) {
        //Room room = roomMapper.toEntity(roomDTO);
        //room = roomRepository.save(room);
        //return roomMapper.toDTO(room);
        return roomRepository.save(roomDTO);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotel_HotelId(hotelId);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room updateRoomStatus(Long roomId, Status status) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setStatus(status);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setRoomType(roomDetails.getRoomType());
        room.setBaseCost(roomDetails.getBaseCost());
        room.setTaxes(roomDetails.getTaxes());
        room.setLocation(roomDetails.getLocation());
        room.setStatus(roomDetails.getStatus());
        return roomRepository.save(room);
    }

    public void enableRoom(Long id, Status enabled) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            Room r = room.get();
            r.setStatus(enabled);
            roomRepository.save(r);
        }
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);
    }

}