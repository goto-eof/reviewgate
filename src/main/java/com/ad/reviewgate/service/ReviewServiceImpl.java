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

    public ReviewDTO get(Long id) {
        return this.reviewMapper.toDTO(this.reviewRepository.findById(id).get());
    }

    @Override
    public void delete(Long id) {
        this.reviewRepository.deleteById(id);
    }

    public ReviewDTO save(ReviewDTO reviewDTO) {
        final Review reviewModelSaved = this.reviewRepository.save(this.reviewMapper.toModel(reviewDTO));
        return this.reviewMapper.toDTO(reviewModelSaved);
    }

    @Override
    public ReviewDTO update(Long id, ReviewDTO reviewDTO) {
        // TODO check id matching
        final Review reviewModelSaved = this.reviewRepository.save(this.reviewMapper.toModel(reviewDTO));
        return this.reviewMapper.toDTO(reviewModelSaved);
    }
}
