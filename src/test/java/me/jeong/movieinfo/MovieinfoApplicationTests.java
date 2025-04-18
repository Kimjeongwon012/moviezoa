package me.jeong.movieinfo;

import me.jeong.movieinfo.domain.Board;
import me.jeong.movieinfo.domain.Post;
import me.jeong.movieinfo.domain.Review;
import me.jeong.movieinfo.repository.BoardRepository;
import me.jeong.movieinfo.repository.PostRepository;
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
    private PostRepository postRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void contextLoads() {
        Board board = new Board();
        board.setName("영화잡담");
        boardRepository.save(board);
    }

}
