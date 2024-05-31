package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplRoomMapper implements GenericMapper<RoomRequest, RoomResponse, Room> {

    private final ModelMapper modelMapper;

    @Override
    public RoomResponse toDto(Room room) {
        return modelMapper.map(room, RoomResponse.class);
    }

    @Override
    public Room toEntity(RoomRequest roomRequest) {
        return modelMapper.map(roomRequest, Room.class);
    }

}