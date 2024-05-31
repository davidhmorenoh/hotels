package com.management.hotels.application.services;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final GenericMapper<RoomRequest, RoomResponse, Room> roomMapper;
    private final GenericMapper<StatusDto, StatusDto, Status> statusMapper;

    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room room = roomMapper.toEntity(roomRequest);
        return roomMapper.toDto(roomRepository.save(room));
    }

    public RoomResponse getRoomById(Long id) {
        return roomMapper.toDto(roomRepository.findById(id));
    }

    public List<RoomResponse> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotel_HotelId(hotelId).stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream().map(roomMapper::toDto).collect(Collectors.toList());
    }

    public RoomResponse updateRoomStatus(Long roomId, StatusDto statusDto) {
        Room room = roomRepository.findById(roomId);
        room.setStatus(statusMapper.toEntity(statusDto));
        return roomMapper.toDto(roomRepository.save(room));
    }

    public RoomResponse updateRoom(Long id, RoomRequest roomDetails) {
        Room roomUpdated = roomRepository.findById(id);

        Room roomToUpdate = roomMapper.toEntity(roomDetails);

        roomUpdated.setRoomType(roomToUpdate.getRoomType());
        roomUpdated.setBaseCost(roomToUpdate.getBaseCost());
        roomUpdated.setTaxes(roomToUpdate.getTaxes());
        roomUpdated.setLocation(roomToUpdate.getLocation());
        roomUpdated.setStatus(roomToUpdate.getStatus());
        return roomMapper.toDto(roomRepository.save(roomUpdated));
    }

    public RoomResponse enableRoom(Long id, StatusDto enabled) {
        Room room = roomRepository.findById(id);
        room.setStatus(statusMapper.toEntity(enabled));
        return roomMapper.toDto(roomRepository.save(room));
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id);
        roomRepository.delete(room);
    }

}