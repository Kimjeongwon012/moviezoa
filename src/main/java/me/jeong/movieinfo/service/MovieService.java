package me.jeong.movieinfo.service;

import me.jeong.movieinfo.external.TmdbAPI;
import me.jeong.movieinfo.service.dto.MovieDTO;
import me.jeong.movieinfo.service.mapper.MovieMapper;
import me.jeong.movieinfo.utils.DateUtils;
import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    private final TmdbAPI tmdbAPI;


    public MovieService(MovieRepository movieRepository, TmdbAPI tmdbAPI) {
        this.movieRepository = movieRepository;
        this.tmdbAPI = tmdbAPI;
    }

    public List<MovieDTO> getPopularMovies(int amount) {
        String oneMonthAgo = DateUtils.convertToString(LocalDate.now().minusMonths(1));
        String oneMonthLater = DateUtils.convertToString(LocalDate.now().plusMonths(1));
        System.out.println(oneMonthAgo);
        List<Movie> movies = movieRepository.findMostPopularMovies(oneMonthAgo, oneMonthLater, PageRequest.of(0, amount));
        List<MovieDTO> dtoMovies = MovieMapper.toDtoList(movies);
        return dtoMovies;
    }

    public MovieDTO getMovieDetails(Long movieId) {
        return tmdbAPI.fetchMovieDetails(movieId);
    }
}
