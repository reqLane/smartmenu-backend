package com.naukma.smartmenubackend.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, String>> handleExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap(List.of(e.getMessage())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Map<String, String>> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap(List.of(e.getMessage())));
    }

    @ExceptionHandler(InvalidEntityDataException.class)
    public final ResponseEntity<Map<String, String>> handleInvalidEntityDataExceptions(InvalidEntityDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap(List.of(e.getMessage())));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Map<String, String>> handleEntityNotFoundExceptions(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap(List.of(e.getMessage())));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Map<String, String>> handleBadCredentialsExceptions(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap(List.of(e.getMessage())));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public final ResponseEntity<Map<String, String>> handleInternalAuthenticationServiceExceptions(InternalAuthenticationServiceException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap(List.of(e.getMessage())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Map<String, String>> handleDataIntegrityViolationExceptions(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMap(List.of(e.getMessage())));
    }

    private Map<String, String> errorMap(List<String> messages) {
        Map<String, String> map = new LinkedHashMap<>();
        for(int i = 0; i < messages.size(); i++) {
            map.put("Error#"+(i+1), messages.get(i));
        }
        return map;
    }
}
