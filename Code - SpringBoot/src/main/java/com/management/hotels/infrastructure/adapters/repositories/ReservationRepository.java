package com.management.hotels.infrastructure.adapters.repositories;

import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByTraveler(User traveler);

}