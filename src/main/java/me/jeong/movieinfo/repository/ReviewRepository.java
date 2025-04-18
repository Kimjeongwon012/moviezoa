package me.jeong.movieinfo.repository;

import me.jeong.movieinfo.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByMovieId(Long movieId, Pageable pageable);

    @Query("SELECT COALESCE(SUM(r.rating), 0) FROM Review r where r.movieId = :id")
    Long getTotalRating(@Param("id") Long id);

    int countByMovieId(@Param("id") Long id);
}

