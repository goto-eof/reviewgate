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

    public ReviewMapper() {
        super(Review.class, ReviewDTO.class);

    }

    Converter<Set<ReviewPicture>, Set<String>> MODEL_TO_DTO = mappingContext -> Optional.ofNullable(mappingContext.getSource()).orElseGet(() -> new HashSet<>()).stream().map(reviewPicture -> new String(Base64.getEncoder().encode(reviewPicture.getPicture()))).collect(Collectors.toSet());
    Converter<Set<String>, Set<ReviewPicture>> DTO_TO_MODEL = mappingContext -> Optional.ofNullable(mappingContext.getSource()).orElseGet(() -> new HashSet<>()).stream().map(reviewPictureBase64String -> {
        ReviewPicture reviewPicture = new ReviewPicture();
        reviewPicture.setPicture(Base64.getDecoder().decode(reviewPictureBase64String));
        return reviewPicture;
    }).collect(Collectors.toSet());

    @PostConstruct
    public void postConstruct() {

        getModelMapper().typeMap(Review.class, ReviewDTO.class).addMappings(mapper -> {
            mapper.using(MODEL_TO_DTO).<Set<String>>map(Review::getReviewPictureSet, ReviewDTO::setReviewPictureSet);
        });
        getModelMapper().typeMap(ReviewDTO.class, Review.class).addMappings(mapper -> {
            mapper.using(DTO_TO_MODEL).<Set<ReviewPicture>>map(ReviewDTO::getReviewPictureSet, Review::setReviewPictureSet);
        });

        getModelMapper().typeMap(ReviewDTO.class, Review.class).addMappings(mapper -> {
            mapper.<Review>map(src -> src, (db, review) -> {
                Optional.ofNullable(db.getReviewPictureSet()).orElseGet(() -> new HashSet<ReviewPicture>()).forEach(reviewPicture -> {
                    reviewPicture.setReview(review);
                });
            });
        });
    }
}
