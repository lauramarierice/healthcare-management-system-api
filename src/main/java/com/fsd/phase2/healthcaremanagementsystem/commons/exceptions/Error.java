package com.fsd.phase2.healthcaremanagementsystem.commons.exceptions;

public class Error {
    private Long code;
    private String message;

    public static Error.ErrorBuilder builder() {
        return new Error.ErrorBuilder();
    }

    public Long getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error() {
    }

    public static class ErrorBuilder {
        private Long code;
        private String message;

        ErrorBuilder() {
        }

        public Error.ErrorBuilder code(Long code) {
            this.code = code;
            return this;
        }

        public Error.ErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            return new Error(this.code, this.message);
        }

        public String toString() {
            return "Error.ErrorBuilder(code=" + this.code + ", message=" + this.message + ")";
        }
    }
}
