package me.jeong.movieinfo.controller;

import me.jeong.movieinfo.entity.Movie;
import me.jeong.movieinfo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService service;

    @GetMapping
    public List<Movie> getMovies() {
        return service.getAllMovies();
    }
}
