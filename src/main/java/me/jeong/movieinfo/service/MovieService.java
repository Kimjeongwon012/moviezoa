package me.jeong.movieinfo.service;

import me.jeong.movieinfo.component.DateUtils;
import me.jeong.movieinfo.controller.MovieController;
import me.jeong.movieinfo.entity.Actor;
import me.jeong.movieinfo.entity.Movie;
import me.jeong.movieinfo.repository.ActorRepository;
import me.jeong.movieinfo.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;


    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public List<Movie> getPopularMovies(int amount) {
        String oneMonthAgo = DateUtils.convertToString(LocalDate.now().minusMonths(1));
        String oneMonthLater = DateUtils.convertToString(LocalDate.now().plusMonths(1));
        List<Movie> movies = movieRepository.findMostPopularMovies(oneMonthAgo, oneMonthLater, PageRequest.of(0, amount));
        return movies;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 영화를 요청함"));
    }
}
