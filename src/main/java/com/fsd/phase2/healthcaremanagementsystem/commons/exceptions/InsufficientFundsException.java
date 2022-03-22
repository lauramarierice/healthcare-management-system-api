package com.fsd.phase2.healthcaremanagementsystem.commons.exceptions;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}