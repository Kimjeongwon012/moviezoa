package me.jeong.movieinfo.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.service.dto.MovieDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static MovieDTO mapToMovieDTO(String movieData) throws JsonProcessingException {
        return objectMapper.readValue(movieData, MovieDTO.class);
    }

    public static Movie mapToMovie(Map<String, Object> movieData) {
        return objectMapper.convertValue(movieData, Movie.class);
    }

    public static MovieDTO toDto(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieDTO dto = new MovieDTO();

        dto.setId(movie.getId());
        dto.setOverview(movie.getOverview());
        dto.setTitle(movie.getTitle());
        dto.setOriginalTitle(movie.getOriginal_title());
        dto.setReleaseDate(movie.getRelease_date());
        dto.setPopularity(movie.getPopularity());
        dto.setRating(movie.getRating());
        dto.setBackdropPath(movie.getBackdrop_path());
        dto.setPosterPath(movie.getPoster_path());

        return dto;
    }

    public static List<MovieDTO> toDtoList(List<Movie> movies) {
        if (movies == null) {
            return null;
        }

        List<MovieDTO> dtoList = new ArrayList<>();
        for (Movie movie : movies) {
            dtoList.add(toDto(movie));
        }
        return dtoList;
    }

}
