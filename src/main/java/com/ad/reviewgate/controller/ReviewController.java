package com.ad.reviewgate.controller;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {

    final private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(this.reviewService.save(reviewDTO));
    }
}
