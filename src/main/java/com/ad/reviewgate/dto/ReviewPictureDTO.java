package com.ad.reviewgate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
public class ReviewPictureDTO {
    private Long id;
    private String picture;

    @Override
    public String toString() {
        return "ReviewPictureDTO{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                '}';
    }
}
