package com.ad.reviewgate.mapper;

import com.ad.reviewgate.dto.ReviewDTO;
import com.ad.reviewgate.model.Review;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class ReviewMapper extends MapperCommon<Review, ReviewDTO> {

    public ReviewMapper() {
        super(Review.class, ReviewDTO.class);
    }

    @PostConstruct
    public void postConstruct() {
        getModelMapper().typeMap(ReviewDTO.class, Review.class, "LAZY")
                .addMappings(mapper -> mapper.skip(Review::setReviewPictureSet));


    }
}
