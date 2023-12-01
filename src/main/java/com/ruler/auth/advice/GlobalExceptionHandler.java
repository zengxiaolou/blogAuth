package com.ruler.auth.advice;

import com.ruler.auth.exception.BaseCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(BaseCustomException.class)
    public ResponseEntity<?> handleBaseCustomException(BaseCustomException e) {
        String errorCode = e.getErrorCode();
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("message",  e.getMessage());
        errorAttributes.put("errorCode", errorCode);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(errorAttributes);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e) {
        logger.error(e.getMessage());
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("message", "A general error occurred: " + e.getMessage());
        errorAttributes.put("error", "GeneralError");
        errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorAttributes);
    }


}
