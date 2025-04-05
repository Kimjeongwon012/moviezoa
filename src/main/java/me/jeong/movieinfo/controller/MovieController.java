package me.jeong.movieinfo.controller;

import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.service.MovieService;
import me.jeong.movieinfo.service.dto.MovieDTO;
import me.jeong.movieinfo.service.dto.ReviewDTO;
import me.jeong.movieinfo.service.dto.ReviewRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
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
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long id) {
        try {
            MovieDTO movie = movieService.getMovieDetails(id);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

    }

    @GetMapping("/{id}/review")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable("id") Long id,
                                                   @RequestParam("page") int page,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("sort") String sort) {
        try {
            String[] sortParams = (sort == null || sort.isEmpty())
                    ? new String[]{"createdAt", "desc"} // 정렬 기본값
                    : sort.split(",");
            Pageable pageable = PageRequest.of(
                    Math.max(0, page - 1), // 프론트는 1부터, 백은 0부터 → 보정
                    size,
                    Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
            );
            ReviewDTO reviews = movieService.getReviewsByMovieId(id, pageable);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }


    @PostMapping("/{id}/review")
    public ResponseEntity<Void> writeReview(@PathVariable("id") Long id,
                                            @RequestBody ReviewRequestDTO dto) {
        try {
            movieService.writeReview(id, dto.getContent(), dto.getRating());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }
}
