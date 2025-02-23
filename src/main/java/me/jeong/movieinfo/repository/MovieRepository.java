package me.jeong.movieinfo.repository;

import jakarta.persistence.Column;
import lombok.Value;
import me.jeong.movieinfo.entity.Movie;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    /**
     * 영화 테이블에서 모든 영화의 ID들을 가져온다.
     *
     * @return 모든 영화 ID 목록
     */
    @Query("SELECT m.id FROM Movie m")
    Set<Long> findAllIds();

    @Query("SELECT m FROM Movie m WHERE m.release_date BETWEEN :start_date AND :end_date ORDER BY m.popularity DESC")
    List<Movie> findMostPopularMovies(
            @Param("start_date") String startDate,
            @Param("end_date") String endDate,
            Pageable pageable
    );
}
