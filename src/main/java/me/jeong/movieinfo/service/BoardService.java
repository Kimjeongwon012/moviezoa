package me.jeong.movieinfo.service;

import lombok.RequiredArgsConstructor;
import me.jeong.movieinfo.domain.Board;
import me.jeong.movieinfo.service.mapper.PostMapper;
import me.jeong.movieinfo.repository.BoardRepository;
import me.jeong.movieinfo.repository.PostRepository;
import me.jeong.movieinfo.service.dto.PostDTO;
import me.jeong.movieinfo.service.dto.PostRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private static final Logger log = LoggerFactory.getLogger(BoardService.class);

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public List<PostDTO> getPostsByBoard(Long boardId, Pageable pageable) {
        List<PostDTO> posts = PostMapper.toDtoList(postRepository.findByBoardId(boardId, pageable));
        return posts;
    }

    public Board getBoardById(Long boardId) {
        return boardRepository.findBoardById(boardId);
    }

    public void writePost(int boardId, PostRequestDTO dto) {
        // 첨부파일, 작성 내용을 DB 에 저장
    }
}
