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

    /**
     * 한 달 전부터 한 달 후까지 개봉한 인기 영화를 조회한다.
     *
     * @param amount 가져올 인기 영화 수
     * @return 인기 영화 목록 (DTO 형태)
     */
    public List<MovieDTO> getPopularMovies(int amount) {
        String oneMonthAgo = DateUtils.convertToString(LocalDate.now().minusMonths(1));
        String oneMonthLater = DateUtils.convertToString(LocalDate.now().plusMonths(1));
        List<Movie> movies = movieRepository.findMostPopularMovies(oneMonthAgo, oneMonthLater, PageRequest.of(0, amount));
        List<MovieDTO> dtoMovies = MovieMapper.toDtoList(movies);
        return dtoMovies;
    }

    /**
     * TMDB API를 통해 영화 상세 정보를 가져오고,
     * 해당 영화에 대한 리뷰도 함께 조회하여 설정한다.
     *
     * @param id 상세 정보를 조회할 영화 ID
     * @return 영화 상세 정보 (리뷰 포함, DTO 형태)
     */
    public MovieDTO getMovieDetails(Long id) {
        MovieDTO dto = tmdbAPI.fetchMovieDetails(id);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(0, 10, sort);
        dto.setReview(getReviewsByMovieId(id, pageable));
        return dto;
    }

    /**
     * 영화 ID를 기반으로 해당 영화에 대한 리뷰를 페이지 단위로 조회하고,
     * 평균 평점, 리뷰 수 등의 정보를 포함한 DTO를 반환한다.
     *
     * @param id       리뷰를 조회할 영화 ID
     * @param pageable 페이징 및 정렬 정보
     * @return 리뷰 정보 DTO (평균 평점, 리뷰 목록 포함)
     */
    public ReviewDTO getReviewsByMovieId(Long id, Pageable pageable) {
        ReviewDTO dto = new ReviewDTO();

        List<Review> reviews = reviewRepository.findReviewsByMovieId(id, pageable);

        double rattingSum = reviewRepository.getTotalRating(id);
        int rattingSize = reviewRepository.countByMovieId(id);
        if (rattingSize != 0 && rattingSum != 0) {
            double avgRating = rattingSum / rattingSize;
            dto.setAvgRating(avgRating);
        }
        dto.setReviews(ReviewMapper.toDtoList(reviews));
        dto.setTotalCount(rattingSize);
        dto.setLength(reviews.size());

        return dto;
    }

    /**
     * 주어진 영화 ID를 기반으로 리뷰를 생성하여 저장한다.
     *
     * @param id      리뷰를 작성할 대상 영화의 ID
     * @param content 리뷰 내용
     * @param rating  리뷰 평점 (1~5)
     */
    public void writeReview(Long id, String content, int rating) {
        Review review = new Review();
        review.setMovieId(id);
        review.setContent(content);
        review.setRating(rating);
        reviewRepository.save(review);
    }
}
