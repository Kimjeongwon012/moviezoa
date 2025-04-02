package me.jeong.movieinfo;

import me.jeong.movieinfo.domain.Review;
import me.jeong.movieinfo.repository.ReviewRepository;
import me.jeong.movieinfo.utils.DateUtils;
import me.jeong.movieinfo.external.TmdbAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieinfoApplicationTests {

    @Autowired
    private TmdbAPI tmdbAPI;
    @Autowired
    private DateUtils date;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void contextLoads() {
        Review review = new Review();
        review.setContent("고양이가 귀여워요");
        review.setRating(4);
        review.setMovieId((long) 823219);
        reviewRepository.save(review);
    }

}
