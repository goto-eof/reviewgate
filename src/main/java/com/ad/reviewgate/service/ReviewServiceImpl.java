package com.ad.reviewgate.service;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.dto.ReviewPictureDTO;
import com.ad.reviewgate.exception.ApplicationException;
import com.ad.reviewgate.mapper.ReviewMapper;
import com.ad.reviewgate.mapper.ReviewPictureMapper;
import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.model.ReviewPicture;
import com.ad.reviewgate.repository.ReviewPictureRepository;
import com.ad.reviewgate.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final private ReviewMapper reviewMapper;
    final private ReviewPictureMapper reviewPictureMapper;
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
        Set<ReviewPictureDTO> reviewPictureSet = new HashSet<>();
        reviewDTO.getReviewPictureSet().stream().forEach(reviewPictureDTO -> {
            ReviewPicture model = this.reviewPictureMapper.toModel(reviewPictureDTO);
            model.setReview(reviewModelSaved);
            reviewPictureSet.add(this.reviewPictureMapper.toDTO(this.reviewPictureRepository.save(model)));
        });
        ReviewDTO reviewDTOSaved = this.reviewMapper.toDTO(reviewModelSaved);
        reviewDTOSaved.setReviewPictureSet(reviewPictureSet);
        return reviewDTOSaved;
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
        System.out.println(reviewDTO);
        Set<ReviewPicture> reviewPictureSet = reviewDTO.getReviewPictureSet().stream().map(reviewPictureDTO -> {
            ReviewPicture reviewPicture = reviewPictureRepository.findById(reviewPictureDTO.getId()).get();
            this.reviewPictureMapper.getModelMapper().map(reviewPictureDTO, reviewPicture);
            return reviewPicture;
        }).collect(Collectors.toSet());

        this.reviewPictureRepository.saveAll(reviewPictureSet);
        Review model = this.reviewRepository.findById(id).get();
        this.reviewMapper.getModelMapper().map(reviewDTO, model, "LAZY");
        final Review reviewModelSaved = this.reviewRepository.save(model);
        return this.reviewMapper.toDTO(reviewModelSaved);
    }
}
