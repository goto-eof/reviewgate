package com.ad.reviewgate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Ciao");
    }
}