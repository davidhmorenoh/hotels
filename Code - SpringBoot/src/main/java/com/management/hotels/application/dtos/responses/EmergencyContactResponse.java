package com.management.hotels.application.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @JsonProperty("id")
    private long contactId;

    private String fullName;

    @JsonProperty("phone")
    private String contactPhone;

    private ReservationResponse reservation;

}