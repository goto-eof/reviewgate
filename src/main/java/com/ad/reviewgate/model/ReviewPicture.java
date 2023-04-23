package com.ad.reviewgate.model;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "rg_review_picture")
@ToString(exclude = "review")
public class ReviewPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    private byte[] picture;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
