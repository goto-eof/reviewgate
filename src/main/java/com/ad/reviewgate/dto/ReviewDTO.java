package com.ad.reviewgate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class ReviewDTO {
    private Long id;
    private String title;
    private String description;
    private Integer rating;
    private Set<ReviewPictureDTO> reviewPictureSet;
}
