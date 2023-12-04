package com.ruler.auth.exception;

public class CodeException extends BaseCustomException {
    private static final String CODE = "CODE";
    public CodeException(String message) {
        super(CODE, message);
    }
}
