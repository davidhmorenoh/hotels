package com.management.hotels.domain.entities;

import com.management.hotels.domain.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "travelerId", nullable = false)
    private User traveler;

    @Column(nullable = false)
    private Date checkInDate;

    @Column(nullable = false)
    private Date checkOutDate;

    @Column(nullable = false)
    private Integer numberOfGuests;

    @Column(nullable = false, updatable = false)
    private Timestamp reservationDate = new Timestamp(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "reservation")
    private List<Guest> guests;

    @OneToMany(mappedBy = "reservation")
    private List<EmergencyContact> emergencyContacts;

}