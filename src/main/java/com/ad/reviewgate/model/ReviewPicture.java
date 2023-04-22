package com.ad.reviewgate.model;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "rg_review_picture")
public class ReviewPicture {

    @Id
    @Column(name = "id")
    private Long id;

    @Lob
    private Blob picture;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
