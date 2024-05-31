package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRoomUseCase {

    private final RoomService roomService;

    public void execute(Long id) {
        roomService.deleteRoom(id);
    }

}