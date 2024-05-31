package com.management.hotels.application.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.GenderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class GuestResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @JsonProperty("id")
    private long guestId;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    @JsonProperty("gender")
    private GenderDto genderDto;

    private String documentType;

    private String documentNumber;

    private String email;

    private String contactPhone;

    private ReservationResponse reservation;

}