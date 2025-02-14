package com.example.UserService.exception;


public class UserProfileCreationException extends RuntimeException {
    public UserProfileCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
