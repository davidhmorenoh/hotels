package com.management.hotels.application.dtos.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableRoomsRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotNull(message = "Available rooms check in date is mandatory")
    @FutureOrPresent(message = "Available rooms check in date must be equal or after to current date")
    private Date checkInDate;

    @NotNull(message = "Available rooms check out date is mandatory")
    @FutureOrPresent(message = "Available rooms check out date must be equal or after to current date")
    private Date checkOutDate;

    @NotNull(message = "Available rooms capacity is mandatory")
    private int capacity;

    @NotBlank(message = "Available rooms city is mandatory")
    @Size(min = 3, max = 100, message = "Available rooms city must be between 3 and 100 characters")
    private String city;

}