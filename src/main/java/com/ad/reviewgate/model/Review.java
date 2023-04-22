package com.ad.reviewgate.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "rg_review")
public class Review extends ModelCommon {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @OneToMany(mappedBy="review")
    private Set<ReviewPicture> reviewPictureList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
