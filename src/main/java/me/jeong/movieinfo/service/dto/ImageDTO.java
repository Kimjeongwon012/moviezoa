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
public class ImageDTO {
    private List<MovieImageDTO> backdrops;
    private List<MovieImageDTO> logos;
    private List<MovieImageDTO> posters;
}
