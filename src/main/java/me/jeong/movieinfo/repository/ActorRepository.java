package me.jeong.movieinfo.repository;

import me.jeong.movieinfo.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    /**
     * 배우 테이블에서 모든 배우의 ID들을 가져온다.
     *
     * @return 모든 배우 ID 목록
     */
    @Query("SELECT a.id FROM Actor a")
    Set<Long> findAllIds();
}
