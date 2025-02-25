package me.jeong.movieinfo.controller;

import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService service;

    @GetMapping("/top-popular/{amount}")
    public List<Movie> getPopularMovies(@PathVariable("amount") int amount) {
        return service.getPopularMovies(amount);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") Long id) {
        Movie movie = service.getMovieById(id);
        log.info(movie.getBackdrop_path());
        return service.getMovieById(id);
    }
}
