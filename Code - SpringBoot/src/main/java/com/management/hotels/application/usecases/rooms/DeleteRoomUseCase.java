package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.services.RoomApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRoomUseCase {

    private final RoomApplicationService roomApplicationService;

    public void execute(Long id, Long userId) {
        roomApplicationService.deleteRoom(id, userId);
    }

}