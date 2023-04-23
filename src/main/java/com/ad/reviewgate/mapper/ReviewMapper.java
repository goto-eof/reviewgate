package com.ad.reviewgate.mapper;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.model.ReviewPicture;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ReviewMapper extends MapperCommon<Review, ReviewDTO> {
    final static Converter<Set<ReviewPicture>, Set<String>> MODEL_TO_DTO = mappingContext ->
            Optional.ofNullable(mappingContext.getSource())
                    .orElseGet(() -> new HashSet<>())
                    .stream()
                    .map(reviewPicture -> new String(Base64.getEncoder().encode(reviewPicture.getPicture())))
                    .collect(Collectors.toSet());
    final static Converter<Set<String>, Set<ReviewPicture>> DTO_TO_MODEL = mappingContext ->
            Optional.ofNullable(mappingContext.getSource())
                    .orElseGet(() -> new HashSet<>())
                    .stream().map(reviewPictureBase64String -> {
                        ReviewPicture reviewPicture = new ReviewPicture();
                        reviewPicture.setPicture(Base64.getDecoder().decode(reviewPictureBase64String));
                        reviewPicture.setReview((Review) mappingContext.getParent().getDestination());
                        return reviewPicture;
                    }).collect(Collectors.toSet());

    public ReviewMapper() {
        super(Review.class, ReviewDTO.class);
    }

    @PostConstruct
    public void postConstruct() {

        getModelMapper().typeMap(Review.class, ReviewDTO.class).addMappings(mapper -> {
            mapper.using(MODEL_TO_DTO).<HashSet<String>>map(Review::getReviewPictureSet, ReviewDTO::setReviewPictureSet);
        });
        getModelMapper().typeMap(ReviewDTO.class, Review.class).addMappings(mapper -> {
            mapper.using(DTO_TO_MODEL).<HashSet<ReviewPicture>>map(ReviewDTO::getReviewPictureSet, Review::setReviewPictureSet);
        });

    }
}
