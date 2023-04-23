package com.ad.reviewgate.service;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.exception.ApplicationException;

public interface ReviewService {
    ReviewDTO save(ReviewDTO reviewDTO);
    ReviewDTO update(Long id, ReviewDTO reviewDTO) throws ApplicationException;
    ReviewDTO get(Long id);
    void delete(Long id);
}
