package com.enes.file.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.naming.SizeLimitExceededException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity handleMultipartException(MultipartException e) {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Max file size must be 5mb");
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(message);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleDeleteFileException(EmptyResultDataAccessException e) {
        Map<String, String> message = new HashMap<>();
        message.put("message", "File not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleDeleteFileException2(NoSuchElementException e) {
        Map<String, String> message = new HashMap<>();
        message.put("message", "File not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
