package me.jeong.movieinfo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private List<MovieReviewDTO> reviews;
    private int length;
    private int totalCount;
    private double avgRating;
}
