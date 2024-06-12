package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.AvailableRoomsRequest;
import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.services.RoomDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomApplicationService {

    private final RoomDomainService roomDomainService;

    private final GenericMapper<RoomRequest, RoomResponse, Room> roomMapper;

    public List<RoomResponse> getAllRooms() {
        return roomDomainService.getAllRooms().stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public RoomResponse getRoomById(Long id, Long userId) {
        Room room = roomDomainService.validateUserOwnsRoom(id, userId);
        return roomMapper.toDto(room);
    }

    public List<RoomResponse> getRoomsByHotelId(Long hotelId, Long userId) {
        return roomDomainService.getRoomsByHotelId(hotelId, userId).stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public List<RoomResponse> findAvailableRooms(AvailableRoomsRequest availableRoomsRequest, Long userId) {
        return roomDomainService.findAvailableRooms(
                        userId,
                        availableRoomsRequest.getCity(),
                        availableRoomsRequest.getCheckInDate(),
                        availableRoomsRequest.getCheckOutDate(),
                        availableRoomsRequest.getCapacity())
                .stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public RoomResponse createRoom(RoomRequest roomRequest, Long userId) {
        Room room = roomMapper.toEntity(roomRequest);
        Room createdRoom = roomDomainService.createRoom(room, roomRequest.getHotelId(), userId);
        return roomMapper.toDto(createdRoom);
    }

    public RoomResponse updateRoom(Long id, RoomRequest roomRequest, Long userId) {
        Room roomToUpdate = roomMapper.toEntity(roomRequest);
        Room updatedRoom = roomDomainService.updateRoom(id, roomToUpdate, userId);
        return roomMapper.toDto(updatedRoom);
    }

    public RoomResponse enableRoom(Long id, Long userId) {
        Room enabledRoom = roomDomainService.enableRoom(id, userId);
        return roomMapper.toDto(enabledRoom);
    }

    public RoomResponse disableRoom(Long id, Long userId) {
        Room disabledRoom = roomDomainService.disableRoom(id, userId);
        return roomMapper.toDto(disabledRoom);
    }

    public void deleteRoom(Long id, Long userId) {
        roomDomainService.deleteRoom(id, userId);
    }

}