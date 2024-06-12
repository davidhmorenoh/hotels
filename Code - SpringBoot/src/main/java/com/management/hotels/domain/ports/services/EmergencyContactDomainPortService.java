package com.management.hotels.domain.ports.services;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Reservation;

import java.util.List;

public interface EmergencyContactDomainPortService {

    List<EmergencyContact> getEmergencyContactsByReservation(Reservation reservation);

    List<EmergencyContact> assignReservationAndSaveContacts(List<EmergencyContact> emergencyContacts, Reservation reservation);

}