package me.jeong.movieinfo.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.domain.Movie;

import java.util.Map;

public class MovieMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Movie mapToMovie(Map<String, Object> movieData) {
        return objectMapper.convertValue(movieData, Movie.class);
    }
}
