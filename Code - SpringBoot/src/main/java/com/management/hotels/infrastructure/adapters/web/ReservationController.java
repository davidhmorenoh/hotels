package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.enums.StateDto;
import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.usecases.reservations.*;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(getAllReservationsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(getReservationByIdUseCase.execute(id));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(getReservationsByRoomUseCase.execute(roomId));
    }

    @GetMapping("/traveler/{travelerId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByTraveler(@PathVariable Long travelerId) {
        return ResponseEntity.ok(getReservationsByTravelerUseCase.execute(travelerId));
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(createReservationUseCase.execute(reservationRequest));
    }

    @PatchMapping("/status/{reservationId}")
    public ResponseEntity<ReservationResponse> updateReservationStatus(@PathVariable Long reservationId, @RequestParam StateDto state) {
        return ResponseEntity.ok(updateReservationStatusUseCase.execute(reservationId, state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        deleteReservationUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}