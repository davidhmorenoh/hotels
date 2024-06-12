package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.exceptions.rooms.RoomAlreadyDisabledException;
import com.management.hotels.domain.exceptions.rooms.RoomAlreadyEnabledException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomDomainService {

    private final RoomRepository roomRepository;

    private final UserDomainService userDomainService;
    private final HotelDomainService hotelDomainService;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getRoomsByHotelId(Long hotelId, Long userId) {
        Hotel hotel = hotelDomainService.validateUserOwnsHotel(hotelId, userId);
        return roomRepository.findByHotel(hotel);
    }

    public List<Room> findAvailableRooms(Long userId, String city, Date checkIn, Date checkOut, int capacity) {
        userDomainService.validateUserIsTraveler(userId);
        return roomRepository.findAvailableRooms(city, checkIn, checkOut, capacity);
    }

    public Room createRoom(Room room, Long hotelId, Long userId) {
        Hotel hotel = hotelDomainService.validateUserOwnsHotel(hotelId, userId);
        room.setHotel(hotel);
        if (room.getStatus() == null) {
            room.setStatus(Status.Enabled);
        }
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room updatedRoom, Long userId) {
        Room existingRoom = this.validateUserOwnsRoom(id, userId);
        existingRoom.setRoomType(updatedRoom.getRoomType());
        existingRoom.setBaseCost(updatedRoom.getBaseCost());
        existingRoom.setTaxes(updatedRoom.getTaxes());
        existingRoom.setLocation(updatedRoom.getLocation());
        existingRoom.setCapacity(updatedRoom.getCapacity());
        existingRoom.setStatus(updatedRoom.getStatus());
        return roomRepository.save(existingRoom);
    }

    public Room enableRoom(Long id, Long userId) {
        Room room = this.validateUserOwnsRoom(id, userId);
        if (room.getStatus() == Status.Enabled) {
            throw new RoomAlreadyEnabledException("The room is already enabled.");
        }
        room.setStatus(Status.Enabled);
        return roomRepository.save(room);
    }

    public Room disableRoom(Long id, Long userId) {
        Room room = this.validateUserOwnsRoom(id, userId);
        if (room.getStatus() == Status.Disabled) {
            throw new RoomAlreadyDisabledException("The room is already disabled.");
        }
        room.setStatus(Status.Disabled);
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id, Long userId) {
        Room room = this.validateUserOwnsRoom(id, userId);
        roomRepository.delete(room);
    }

    public Room validateUserOwnsRoom(Long id, Long userId) {
        User user = userDomainService.validateUserIsAgent(userId);
        Room room = this.getRoomById(id);
        if (room.getHotel().getUser() != user) {
            throw new UserNotAuthorizedToPerformOperationException("This agent does not have permission to manage this hotel.");
        }
        return room;
    }

}