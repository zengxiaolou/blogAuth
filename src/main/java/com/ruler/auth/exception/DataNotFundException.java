package com.ruler.auth.exception;

public class DataNotFundException extends BaseCustomException{
    private static final String DATA_NOT_FUND = "DATA_NOT_FUND";
    public DataNotFundException(String message) {
        super(DATA_NOT_FUND, message);
    }
}
