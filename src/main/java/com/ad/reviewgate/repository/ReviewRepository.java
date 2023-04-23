package com.ad.reviewgate.repository;

import com.ad.reviewgate.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
