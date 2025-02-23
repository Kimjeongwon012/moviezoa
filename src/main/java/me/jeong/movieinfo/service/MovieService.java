package me.jeong.movieinfo.service;

import me.jeong.movieinfo.component.DateUtils;
import me.jeong.movieinfo.entity.Actor;
import me.jeong.movieinfo.entity.Movie;
import me.jeong.movieinfo.repository.ActorRepository;
import me.jeong.movieinfo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Value("${base.image.url}")
    private String baseImageUrl;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public List<Movie> getPopularMovies(int amount) {
        String oneMonthAgo = DateUtils.convertToString(LocalDate.now().minusMonths(1));
        String oneMonthLater = DateUtils.convertToString(LocalDate.now().plusMonths(1));
        List<Movie> movies = movieRepository.findMostPopularMovies(oneMonthAgo, oneMonthLater, PageRequest.of(0, amount));
        return updateImagePaths(movies);
    }

    private List<Movie> updateImagePaths(List<Movie> movies) {
        for (Movie movie : movies) {
            movie.setBackdrop_path(baseImageUrl + movie.getBackdrop_path());
            movie.setPoster_path(baseImageUrl + movie.getPoster_path());
        }
        return movies;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 영화를 요청함"));
    }
}
