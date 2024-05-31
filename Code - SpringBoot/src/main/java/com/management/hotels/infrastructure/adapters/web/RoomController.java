package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.dtos.requests.RoomRequest;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.application.usecases.rooms.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomUseCase createRoomUseCase;
    private final GetRoomByIdUseCase getRoomByIdUseCase;
    private final GetRoomsByHotelIdUseCase getRoomsByHotelIdUseCase;
    private final GetAllRoomsUseCase getAllRoomsUseCase;
    private final UpdateRoomStatusUseCase updateRoomStatusUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final EnableRoomUseCase enableRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(getAllRoomsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(getRoomByIdUseCase.execute(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByHotelId(@PathVariable Long hotelId) {
        return ResponseEntity.ok(getRoomsByHotelIdUseCase.execute(hotelId));
    }

    @PostMapping("/create")
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(createRoomUseCase.execute(roomRequest));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<RoomResponse> enableRoom(@PathVariable Long roomId, @RequestParam StatusDto statusDto) {
        return ResponseEntity.ok(updateRoomStatusUseCase.execute(roomId, statusDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomRequest roomDetails) {
        return ResponseEntity.ok(updateRoomUseCase.execute(id, roomDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        deleteRoomUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}