package me.jeong.movieinfo.repository;

import me.jeong.movieinfo.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query()
    List<Post> findByBoardId(Long boardId, Pageable pageable);
}
