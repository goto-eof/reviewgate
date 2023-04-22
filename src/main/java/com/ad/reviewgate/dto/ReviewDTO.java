package com.ad.reviewgate.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private String title;
    private String description;
    private Integer rating;
    private Set<String> reviewPictureSet = new HashSet<>();
}
