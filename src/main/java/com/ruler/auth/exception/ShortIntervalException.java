package com.ruler.auth.exception;

public class ShortIntervalException extends BaseCustomException {
    private static final String SHORT_INTERVAL = "SHORT_INTERVAL";
    public ShortIntervalException(String message) {
        super(SHORT_INTERVAL, message);
    }
}
