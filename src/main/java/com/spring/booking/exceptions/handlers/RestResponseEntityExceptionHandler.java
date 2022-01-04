package com.spring.booking.exceptions.handlers;

import com.spring.booking.exceptions.AuthenticationException;
import com.spring.booking.exceptions.FileWriteException;
import com.spring.booking.exceptions.RegistrationException;
import com.spring.booking.exceptions.WrongArgumentException;
import com.spring.booking.models.responses.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new ErrorResponse(43, "AUTHENTICATION_ERROR", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileWriteException.class)
    public ResponseEntity<Object> handleFileWriteException(FileWriteException e) {
        return new ResponseEntity<>(new ErrorResponse(50, "FILE_ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongArgumentException.class)
    public ResponseEntity<Object> handleWrongArgumentException(WrongArgumentException e) {
        return new ResponseEntity<>(new ErrorResponse(41, "WRONG_ARGUMENT", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Object> handleRegistrationException(RegistrationException e) {
        return new ResponseEntity<>(new ErrorResponse(42, "REGISTRATION_ERROR", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(41, "WRONG_ARGUMENT", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
