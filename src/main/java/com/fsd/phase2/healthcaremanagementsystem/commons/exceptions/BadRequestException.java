package com.fsd.phase2.healthcaremanagementsystem.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private Long errorCode;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Error error) {
        super(error.getMessage());
        this.errorCode = error.getCode();
    }

    public Long getErrorCode() {
        return this.errorCode;
    }
}
