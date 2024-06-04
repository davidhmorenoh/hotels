package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoomUseCase {

    private final RoomService roomService;

    public RoomResponse execute(RoomRequest roomRequest, Long userId) {
        return roomService.createRoom(roomRequest, userId);
    }

}