package com.ad.reviewgate.mapper;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.model.ReviewPicture;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class ReviewMapper extends MapperCommon<Review, ReviewDTO> {

    public ReviewMapper() {
        super(Review.class, ReviewDTO.class);

    }

    @PostConstruct
    public void postConstruct() {
        getModelMapper().getConfiguration().setAmbiguityIgnored(true);
        // converts: MODEL -> DTO
        getModelMapper().typeMap(Review.class, ReviewDTO.class).addMappings(mapper -> {

            mapper.<Set<ReviewPicture>>map(src -> src.getReviewPictureList(), (dto, reviewPictureList) -> {
                Optional.ofNullable(reviewPictureList).orElseGet(() -> new HashSet<>()).stream().forEach(reviewPicture -> {
                    dto.getReviewPictureList().add(new String(Base64.getEncoder().encode(reviewPicture.getPicture())));
                });
            });

        });

        // converts DTO -> MODEL
        getModelMapper().typeMap(ReviewDTO.class, Review.class).addMappings(mapper -> {

            mapper.<List<String>>map(src -> src.getReviewPictureList(), (db, base64List) -> {
                base64List.stream().forEach(base64Image -> {
                    ReviewPicture reviewPicture = new ReviewPicture();
                    reviewPicture.setPicture(Base64.getDecoder().decode(base64Image));
                    db.getReviewPictureList().add(reviewPicture);
                });
            });

            mapper.<Review>map(src -> src, (db, review) -> {
                db.getReviewPictureList().forEach(reviewPicture -> {
                    reviewPicture.setReview(review);
                });
            });

        });
    }
}
