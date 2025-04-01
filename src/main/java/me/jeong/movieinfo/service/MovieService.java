package me.jeong.movieinfo.service;

import me.jeong.movieinfo.domain.Review;
import me.jeong.movieinfo.external.TmdbAPI;
import me.jeong.movieinfo.repository.ReviewRepository;
import me.jeong.movieinfo.service.dto.MovieDTO;
import me.jeong.movieinfo.service.dto.ReviewDTO;
import me.jeong.movieinfo.service.mapper.MovieMapper;
import me.jeong.movieinfo.service.mapper.ReviewMapper;
import me.jeong.movieinfo.utils.DateUtils;
import me.jeong.movieinfo.domain.Movie;
import me.jeong.movieinfo.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final TmdbAPI tmdbAPI;


    public MovieService(MovieRepository movieRepository, ReviewRepository reviewRepository, TmdbAPI tmdbAPI) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.tmdbAPI = tmdbAPI;
    }

    public List<MovieDTO> getPopularMovies(int amount) {
        String oneMonthAgo = DateUtils.convertToString(LocalDate.now().minusMonths(1));
        String oneMonthLater = DateUtils.convertToString(LocalDate.now().plusMonths(1));
        List<Movie> movies = movieRepository.findMostPopularMovies(oneMonthAgo, oneMonthLater, PageRequest.of(0, amount));
        List<MovieDTO> dtoMovies = MovieMapper.toDtoList(movies);
        return dtoMovies;
    }

    public MovieDTO getMovieDetails(Long movieId) {
        MovieDTO dto = tmdbAPI.fetchMovieDetails(movieId);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(0, 10, sort);
        dto.setReview(getReviewsByMovieId(movieId, pageable));
        return dto;
    }
    // TODO: 여기서부터 수정!!!!!

    public ReviewDTO getReviewsByMovieId(Long movieId, Pageable pageable) {
        ReviewDTO dto = new ReviewDTO();
        List<Review> reviews = reviewRepository.findReviewsByMovieId(movieId, pageable);

        double rattingSum = 0;
        for (int idx = 0; idx < reviews.size(); idx++) {
            rattingSum += reviews.get(idx).getRating();
        }
        if (reviews.size() != 0 && rattingSum != 0) {
            double avgRating = rattingSum / reviews.size();
            dto.setAvgRating(avgRating);
        }
        dto.setReviews(ReviewMapper.toDtoList(reviews));
        dto.setLength(reviews.size());

        return dto;
    }
}
