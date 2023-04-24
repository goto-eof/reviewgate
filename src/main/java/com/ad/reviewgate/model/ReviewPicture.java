package com.ad.reviewgate.model;

import jakarta.persistence.*;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

@Entity
@Table(name = "rg_review_picture")
public class ReviewPicture extends ModelCommon{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    private Byte[] picture;

    @ManyToOne(cascade = CascadeType.DETACH)
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

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ReviewPicture{" +
                "id=" + id +
                ", picture=" + new String(ArrayUtils.toPrimitive(picture)) +
                ", review=" + review.getId() +
                '}';
    }
}
