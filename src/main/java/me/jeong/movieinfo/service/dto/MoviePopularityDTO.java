package me.jeong.movieinfo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoviePopularityDTO {
    private long id;
    private Double popularity;
}
