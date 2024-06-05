package com.management.hotels.application.dtos.requests;

import com.management.hotels.application.dtos.enums.GenderDto;
import com.management.hotels.application.validation.ValidEnum;
import jakarta.validation.constraints.*;
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
public class GuestRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotBlank(message = "Guest first name is mandatory")
    @Size(max = 100, message = "Guest first name must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Guest last name is mandatory")
    @Size(max = 100, message = "Guest last name must not exceed 100 characters")
    private String lastName;

    @NotNull(message = "Guest date of birth is mandatory")
    @PastOrPresent(message = "Guest date of birth must be equal or before to current date")
    private Date dateOfBirth;

    @NotNull(message = "Guest gender is mandatory")
    @ValidEnum(enumClass = GenderDto.class, message = "Guest gender must be one of the predefined values: Male, Female or Other")
    private GenderDto gender;

    @NotBlank(message = "Guest document type is mandatory")
    @Size(max = 50, message = "Guest document type must not exceed 50 characters")
    private String documentType;

    @NotBlank(message = "Guest document number is mandatory")
    @Size(max = 50, message = "Guest document number must not exceed 50 characters")
    private String documentNumber;

    @Email(message = "Guest email should be valid")
    @Size(max = 100, message = "Guest email must not exceed 100 characters")
    private String email;

    @Size(max = 20, message = "Guest contact phone must not exceed 20 characters")
    private String contactPhone;

}