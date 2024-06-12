package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Reservation;

import java.util.List;

public interface EmergencyContactRepository {

    List<EmergencyContact> findByReservation(Reservation reservation);

    List<EmergencyContact> saveAll(List<EmergencyContact> emergencyContacts);

}