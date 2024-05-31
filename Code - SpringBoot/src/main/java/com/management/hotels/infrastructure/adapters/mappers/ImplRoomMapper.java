package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.RoomDto;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplRoomMapper implements GenericMapper<RoomDto, Room> {

    private final ModelMapper modelMapper;

    @Override
    public RoomDto toDto(Room room) {
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public Room toEntity(RoomDto roomDto) {
        return modelMapper.map(roomDto, Room.class);
    }

}