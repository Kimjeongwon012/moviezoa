package me.jeong.movieinfo.controller;

import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.service.MovieService;
import me.jeong.movieinfo.service.dto.MovieDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    private MovieService movieService;

    @GetMapping("/top-popular/{amount}")
    public ResponseEntity<List<MovieDTO>> getPopularMovies(@PathVariable("amount") int amount) {
        try {
            List<MovieDTO> movies = movieService.getPopularMovies(amount);
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long movieId) {
        try {
            MovieDTO movie = movieService.getMovieDetails(movieId);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

    }
}
