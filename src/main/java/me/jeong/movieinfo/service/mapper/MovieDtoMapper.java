package me.jeong.movieinfo.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.domain.Actor;
import me.jeong.movieinfo.service.dto.MovieDTO;

import java.util.List;
import java.util.Map;

public class MovieDtoMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static MovieDTO mapToMovieDTO(String movieData) throws JsonProcessingException {
        return objectMapper.readValue(movieData, MovieDTO.class);
    }
}
