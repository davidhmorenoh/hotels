package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.requests.AvailableRoomsRequest;
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

    private final GetAllRoomsUseCase getAllRoomsUseCase;
    private final GetRoomByIdUseCase getRoomByIdUseCase;
    private final GetRoomsByHotelIdUseCase getRoomsByHotelIdUseCase;
    private final GetAvailableRoomsUseCase getAvailableRoomsUseCase;
    private final CreateRoomUseCase createRoomUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final DisableRoomUseCase disableRoomUseCase;
    private final EnableRoomUseCase enableRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;

    @GetMapping("/")
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(getAllRoomsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getRoomByIdUseCase.execute(id, userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByHotelId(@PathVariable Long hotelId, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getRoomsByHotelIdUseCase.execute(hotelId, userId));
    }

    @PostMapping("/available")
    public ResponseEntity<List<RoomResponse>> getAvailableRooms(@Valid @RequestBody AvailableRoomsRequest availableRoomsRequest, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getAvailableRoomsUseCase.execute(availableRoomsRequest, userId));
    }

    @PostMapping("/create")
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest roomRequest, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(createRoomUseCase.execute(roomRequest, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomRequest roomRequest, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(updateRoomUseCase.execute(id, roomRequest, userId));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<RoomResponse> enableRoom(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(enableRoomUseCase.execute(id, userId));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<RoomResponse> disableRoom(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(disableRoomUseCase.execute(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id, @RequestParam("userId") Long userId) {
        deleteRoomUseCase.execute(id, userId);
        return ResponseEntity.noContent().build();
    }

}