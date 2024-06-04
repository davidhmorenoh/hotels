package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.domain.exceptions.authentication.InvalidPasswordException;
import com.management.hotels.domain.exceptions.authentication.InvalidSessionException;
import com.management.hotels.domain.exceptions.hotels.HotelAlreadyDisabledException;
import com.management.hotels.domain.exceptions.hotels.HotelAlreadyEnabledException;
import com.management.hotels.domain.exceptions.hotels.HotelNotFoundException;
import com.management.hotels.domain.exceptions.reservations.ReservationNotFoundException;
import com.management.hotels.domain.exceptions.rooms.RoomAlreadyDisabledException;
import com.management.hotels.domain.exceptions.rooms.RoomAlreadyEnabledException;
import com.management.hotels.domain.exceptions.rooms.RoomNotFoundException;
import com.management.hotels.domain.exceptions.users.UserAlreadyRegisteredException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.exceptions.users.UserNotFoundException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidPasswordException.class, SignatureException.class, InvalidSessionException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleUnauthorizedException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UserNotFoundException.class, RoomNotFoundException.class, ReservationNotFoundException.class, HotelNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserNotAuthorizedToPerformOperationException.class, HotelAlreadyEnabledException.class, HotelAlreadyDisabledException.class, RoomAlreadyEnabledException.class, RoomAlreadyDisabledException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<String> handlePreconditionFailedException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

}