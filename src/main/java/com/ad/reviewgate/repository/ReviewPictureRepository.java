package com.ad.reviewgate.repository;

import com.ad.reviewgate.model.Review;
import com.ad.reviewgate.model.ReviewPicture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ReviewPictureRepository extends CrudRepository<ReviewPicture, Long> {

    @Query("select r.id from ReviewPicture r where review.id = :parentId")
    Set<Long> findIds(@Param("parentId") Long parentId);
}
