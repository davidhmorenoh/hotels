package com.management.hotels.application.controllers;

import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/room/{roomId}")
    public List<Reservation> getReservationsByRoom(@PathVariable Long roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        return reservationService.getReservationsByRoom(room);
    }

    @GetMapping("/traveler/{travelerId}")
    public List<Reservation> getReservationsByTraveler(@PathVariable Long travelerId) {
        User traveler = new User();
        traveler.setUserId(travelerId);
        return reservationService.getReservationsByTraveler(traveler);
    }

    @PostMapping("/create")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PatchMapping("/status/{reservationId}")
    public Reservation updateReservationStatus(@PathVariable Long reservationId, @RequestParam Status status) {
        return reservationService.updateReservationStatus(reservationId, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

}