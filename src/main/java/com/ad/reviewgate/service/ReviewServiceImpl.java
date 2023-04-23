package com.ad.reviewgate.service;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.mapper.ReviewMapper;
import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final private ReviewMapper reviewMapper;
    final private ReviewRepository reviewRepository;

    public ReviewDTO save(ReviewDTO reviewDTO) {
        final Review reviewModel = this.reviewMapper.toModel(reviewDTO);
        System.out.println(reviewModel);
        final Review reviewModelSaved = this.reviewRepository.save(reviewModel);
        return this.reviewMapper.toDTO(reviewModelSaved);
    }
}
