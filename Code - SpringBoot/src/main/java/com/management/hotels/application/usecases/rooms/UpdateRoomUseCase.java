package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.RoomDto;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRoomUseCase {

    private final RoomService roomService;

    public RoomDto execute(Long roomId, RoomDto roomDetails) {
        return roomService.updateRoom(roomId, roomDetails);
    }

}