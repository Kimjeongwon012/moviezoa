package me.jeong.movieinfo.service;

import me.jeong.movieinfo.utils.DateUtils;
import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.repository.ActorRepository;
import me.jeong.movieinfo.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> getMovieById(Long id) {
        movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 영화를 요청함"));

        return
    }
}
