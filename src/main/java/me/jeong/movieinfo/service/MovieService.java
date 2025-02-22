package me.jeong.movieinfo.service;

import me.jeong.movieinfo.entity.Actor;
import me.jeong.movieinfo.entity.Movie;
import me.jeong.movieinfo.repository.ActorRepository;
import me.jeong.movieinfo.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public Movie createMovie(Movie movie, Set<Long> actorIds) {
        Set<Actor> actors = actorRepository.findAllById(actorIds).stream().collect(Collectors.toSet());
        movie.setActors(actors);
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
