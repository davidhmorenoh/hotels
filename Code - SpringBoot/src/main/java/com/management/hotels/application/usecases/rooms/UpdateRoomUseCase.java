package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRoomUseCase {

    private final RoomService roomService;

    public RoomResponse execute(Long id, RoomRequest roomRequest, Long userId) {
        return roomService.updateRoom(id, roomRequest, userId);
    }

}