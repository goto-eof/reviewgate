package com.ad.reviewgate.controller;

import com.ad.reviewgate.exception.ApplicationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ControllerAdviceCustom {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity handleApplicationException(ApplicationException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
