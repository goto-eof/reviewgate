package com.ad.reviewgate.controller;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {

    final private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.reviewService.get(id));
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(this.reviewService.save(reviewDTO));
    }

    @PutMapping
    public ResponseEntity<ReviewDTO> update(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(this.reviewService.save(reviewDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.reviewService.delete(id);
        return ResponseEntity.ok("OK");
    }
}
