package com.management.hotels.domain.entities;

import com.management.hotels.domain.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double baseCost;

    @Column(nullable = false)
    private double taxes;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

}