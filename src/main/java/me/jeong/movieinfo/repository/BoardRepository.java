package me.jeong.movieinfo.repository;

import me.jeong.movieinfo.domain.Board;
import me.jeong.movieinfo.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findBoardById(Long id);
}
