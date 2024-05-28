package com.management.hotels.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "emergencycontacts")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @ManyToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String contactPhone;

}