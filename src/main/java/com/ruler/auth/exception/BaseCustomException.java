package com.ruler.auth.exception;

public class BaseCustomException extends RuntimeException {
    private final String errorCode;

    public BaseCustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
