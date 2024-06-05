package com.management.hotels.infrastructure.adapters.web;

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
    private final GetReservationsByRoomIdUseCase getReservationsByRoomIdUseCase;
    private final GetReservationsByHotelIdUseCase getReservationsByHotelIdUseCase;
    private final GetReservationsByTravelerUseCase getReservationsByTravelerUseCase;
    private final GetAllReservationsUseCase getAllReservationsUseCase;
    private final GetReservationByIdUseCase getReservationByIdUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final DeleteReservationUseCase deleteReservationUseCase;

    @GetMapping("/")
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(getAllReservationsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getReservationByIdUseCase.execute(id, userId));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByRoomId(@PathVariable Long roomId, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getReservationsByRoomIdUseCase.execute(roomId, userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByHotelId(@PathVariable Long hotelId, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getReservationsByHotelIdUseCase.execute(hotelId, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByTraveler(@PathVariable Long userId) {
        return ResponseEntity.ok(getReservationsByTravelerUseCase.execute(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(createReservationUseCase.execute(reservationRequest));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponse> cancelReservation(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(cancelReservationUseCase.execute(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id, @RequestParam("userId") Long userId) {
        deleteReservationUseCase.execute(id, userId);
        return ResponseEntity.noContent().build();
    }

}