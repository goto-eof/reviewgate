package com.ad.reviewgate.service;

import com.ad.reviewgate.dto.ReviewDTO;

public interface ReviewService {
    ReviewDTO save(ReviewDTO reviewDTO);
    ReviewDTO update(Long id, ReviewDTO reviewDTO);
    ReviewDTO get(Long id);
    void delete(Long id);
}
