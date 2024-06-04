package com.management.hotels.application.services;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.exceptions.rooms.RoomAlreadyDisabledException;
import com.management.hotels.domain.exceptions.rooms.RoomAlreadyEnabledException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final HotelService hotelService;

    private final GenericMapper<RoomRequest, RoomResponse, Room> roomMapper;

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public RoomResponse getRoomById(Long id, Long userId) {
        Room room = this.validateAgentRooms(userId, id);
        return roomMapper.toDto(room);
    }

    public List<RoomResponse> getRoomsByHotelId(Long hotelId, Long userId) {
        Hotel hotel = hotelService.validateAgentHotels(userId, hotelId);
        return roomRepository.findByHotel_HotelId(hotel.getHotelId()).stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public RoomResponse createRoom(RoomRequest roomRequest, Long userId) {
        if (Objects.isNull(roomRequest.getStatus())) {
            roomRequest.setStatus(StatusDto.Enabled);
        }

        Room room = roomMapper.toEntity(roomRequest);

        Hotel hotel = hotelService.validateAgentHotels(userId, roomRequest.getHotelId());
        room.setHotel(hotel);

        return roomMapper.toDto(roomRepository.save(room));
    }

    public RoomResponse updateRoom(Long id, RoomRequest roomRequest, Long userId) {
        Room roomUpdated = roomRepository.findById(id);

        Room roomToUpdate = roomMapper.toEntity(roomRequest);

        Hotel hotel = hotelService.validateAgentHotels(userId, roomRequest.getHotelId());

        roomUpdated.setRoomType(roomToUpdate.getRoomType());
        roomUpdated.setBaseCost(roomToUpdate.getBaseCost());
        roomUpdated.setTaxes(roomToUpdate.getTaxes());
        roomUpdated.setLocation(roomToUpdate.getLocation());
        roomUpdated.setCapacity(roomToUpdate.getCapacity());
        roomUpdated.setStatus(roomToUpdate.getStatus());
        roomUpdated.setHotel(hotel);
        return roomMapper.toDto(roomRepository.save(roomUpdated));
    }

    public RoomResponse enableRoom(Long id, Long userId) {
        Room room = this.validateAgentRooms(userId, id);
        if (room.getStatus().equals(Status.Enabled)) {
            throw new RoomAlreadyEnabledException("No updated, because room already is enable");
        }
        room.setStatus(Status.Enabled);
        return roomMapper.toDto(roomRepository.save(room));
    }

    public RoomResponse disableRoom(Long id, Long userId) {
        Room room = this.validateAgentRooms(userId, id);
        if (room.getStatus().equals(Status.Disabled)) {
            throw new RoomAlreadyDisabledException("No updated, because room already is disable");
        }
        room.setStatus(Status.Disabled);
        return roomMapper.toDto(roomRepository.save(room));
    }

    public void deleteRoom(Long id, Long userId) {
        Room room = this.validateAgentRooms(userId, id);
        roomRepository.delete(room);
    }

    private Room validateAgentRooms(Long userId, Long roomId) {
        User user = hotelService.validateUserTypeAgent(userId);
        Room room = roomRepository.findById(roomId);
        if (!room.getHotel().getCreatedBy().equals(user)) {
            throw new UserNotAuthorizedToPerformOperationException("This agent can't see, modified or deleted this room");
        }
        return room;
    }

}