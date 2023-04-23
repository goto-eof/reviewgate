package com.ad.reviewgate.service;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.dto.ReviewPictureDTO;
import com.ad.reviewgate.exception.ApplicationException;
import com.ad.reviewgate.mapper.ReviewMapper;
import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.repository.ReviewPictureRepository;
import com.ad.reviewgate.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.Optional.ofNullable;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final private ReviewMapper reviewMapper;
    final private ReviewRepository reviewRepository;
    final private ReviewPictureRepository reviewPictureRepository;

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
    public ReviewDTO update(Long id, ReviewDTO reviewDTO) throws ApplicationException {
        if (id != reviewDTO.getId()) {
            throw new ApplicationException("id not matching");
        }
        Optional<Set<ReviewPictureDTO>> reviewPictureSetOpt = Optional.ofNullable(reviewDTO.getReviewPictureSet());
        if (reviewPictureSetOpt.isPresent() && reviewPictureSetOpt.get().size() > 0) {
            Set<Long> ids = this.reviewPictureRepository.findIds(id);
            boolean isAtLeastOneIdUnexpected = reviewPictureSetOpt.get().stream().anyMatch(reviewPictureDTO -> !ids.contains(reviewPictureDTO.getId()));
            if (isAtLeastOneIdUnexpected) {
                throw new ApplicationException("Invalid ids");
            }
        }
        Review model = this.reviewMapper.toModel(reviewDTO);
        final Review reviewModelSaved = this.reviewRepository.save(model);
        return this.reviewMapper.toDTO(reviewModelSaved);
    }
}
