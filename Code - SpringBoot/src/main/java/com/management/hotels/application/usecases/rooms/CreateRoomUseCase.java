package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.RoomDto;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoomUseCase {

    private final RoomService roomService;

    public RoomDto execute(RoomDto roomDto) {
        return roomService.createRoom(roomDto);
    }

}