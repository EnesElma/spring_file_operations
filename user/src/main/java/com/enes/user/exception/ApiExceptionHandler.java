package com.enes.user.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleDeleteUserException(EmptyResultDataAccessException e) {
        Map<String, String> message = new HashMap<>();
        message.put("message", "User not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
