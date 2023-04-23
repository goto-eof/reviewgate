package com.ad.reviewgate.mapper;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.dto.ReviewPictureDTO;
import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.model.ReviewPicture;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ReviewMapper extends MapperCommon<Review, ReviewDTO> {
    final static Converter<Set<ReviewPicture>, Set<ReviewPictureDTO>> MODEL_TO_DTO = mappingContext ->
            Optional.ofNullable(mappingContext.getSource())
                    .orElseGet(() -> new HashSet<>())
                    .stream()
                    .map(reviewPicture -> {
                        ReviewPictureDTO dto = new ReviewPictureDTO();
                        dto.setPicture(new String(Base64.getEncoder().encode(reviewPicture.getPicture())));
                        dto.setId(reviewPicture.getId());
                        return dto;
                    })
                    .collect(Collectors.toSet());
    final static Converter<Set<ReviewPictureDTO>, Set<ReviewPicture>> DTO_TO_MODEL = mappingContext ->
            Optional.ofNullable(mappingContext.getSource())
                    .orElseGet(() -> new HashSet<>())
                    .stream().map(reviewPictureDTO -> {
                        ReviewPicture reviewPicture = new ReviewPicture();
                        reviewPicture.setPicture(Base64.getDecoder().decode(reviewPictureDTO.getPicture()));
                        reviewPicture.setReview((Review) mappingContext.getParent().getDestination());
                        reviewPicture.setId(reviewPictureDTO.getId());
                        return reviewPicture;
                    }).collect(Collectors.toSet());

    public ReviewMapper() {
        super(Review.class, ReviewDTO.class);
    }

    @PostConstruct
    public void postConstruct() {

        getModelMapper().typeMap(Review.class, ReviewDTO.class).addMappings(mapper -> {
            mapper.using(MODEL_TO_DTO).<HashSet<ReviewPictureDTO>>map(Review::getReviewPictureSet, ReviewDTO::setReviewPictureSet);
        });
        getModelMapper().typeMap(ReviewDTO.class, Review.class).addMappings(mapper -> {
            mapper.using(DTO_TO_MODEL).<HashSet<ReviewPicture>>map(ReviewDTO::getReviewPictureSet, Review::setReviewPictureSet);
        });

    }
}
