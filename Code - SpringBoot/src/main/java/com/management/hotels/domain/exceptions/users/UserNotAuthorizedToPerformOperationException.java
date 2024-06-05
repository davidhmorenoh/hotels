package com.management.hotels.domain.exceptions.users;

public class UserNotAuthorizedToPerformOperationException extends IllegalArgumentException {

    public UserNotAuthorizedToPerformOperationException(String message) {
        super(message);
    }

}