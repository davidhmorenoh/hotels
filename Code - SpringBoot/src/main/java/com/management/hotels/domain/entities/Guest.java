package com.management.hotels.domain.entities;

import com.management.hotels.domain.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    @ManyToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String documentType;

    @Column(nullable = false)
    private String documentNumber;

    private String email;
    private String contactPhone;

}