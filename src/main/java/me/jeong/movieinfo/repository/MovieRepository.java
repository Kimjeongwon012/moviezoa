package me.jeong.movieinfo.repository;

import jakarta.persistence.Column;
import me.jeong.movieinfo.entity.Movie;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
