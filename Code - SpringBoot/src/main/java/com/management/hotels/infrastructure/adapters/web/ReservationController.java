package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.ReservationDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.usecases.reservations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final GetReservationsByRoomUseCase getReservationsByRoomUseCase;
    private final GetReservationsByTravelerUseCase getReservationsByTravelerUseCase;
    private final GetAllReservationsUseCase getAllReservationsUseCase;
    private final GetReservationByIdUseCase getReservationByIdUseCase;
    private final UpdateReservationStatusUseCase updateReservationStatusUseCase;
    private final DeleteReservationUseCase deleteReservationUseCase;

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(getAllReservationsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(getReservationByIdUseCase.execute(id));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(getReservationsByRoomUseCase.execute(roomId));
    }

    @GetMapping("/traveler/{travelerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByTraveler(@PathVariable Long travelerId) {
        return ResponseEntity.ok(getReservationsByTravelerUseCase.execute(travelerId));
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(createReservationUseCase.execute(reservationDto));
    }

    @PatchMapping("/status/{reservationId}")
    public ResponseEntity<ReservationDto> updateReservationStatus(@PathVariable Long reservationId, @RequestParam StatusDto status) {
        return ResponseEntity.ok(updateReservationStatusUseCase.execute(reservationId, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        deleteReservationUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}