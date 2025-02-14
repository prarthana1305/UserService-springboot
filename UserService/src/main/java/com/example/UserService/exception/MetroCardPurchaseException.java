package com.example.UserService.exception;



public class MetroCardPurchaseException extends RuntimeException {

    public MetroCardPurchaseException(String message) {
        super(message);
    }

    public MetroCardPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
