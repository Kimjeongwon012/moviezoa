package me.jeong.movieinfo.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewRequestDTO {
    private String content;
    private int rating;
}
