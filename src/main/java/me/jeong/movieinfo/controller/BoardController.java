package me.jeong.movieinfo.controller;

import me.jeong.movieinfo.domain.Board;
import me.jeong.movieinfo.domain.Post;
import me.jeong.movieinfo.service.BoardService;
import me.jeong.movieinfo.service.dto.BoardDTO;
import me.jeong.movieinfo.service.dto.PostDTO;
import me.jeong.movieinfo.service.dto.PostRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;


    @GetMapping("/{boardId}/post")
    public ResponseEntity<BoardDTO> getPostsByBoard(@PathVariable("boardId") Long boardId,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("sort") String sort) {
        try {
            String[] sortParams = (sort == null || sort.isEmpty())
                    ? new String[]{"createdAt", "desc"} // 정렬 기본값
                    : sort.split(",");
            Pageable pageable = PageRequest.of(
                    Math.max(0, page - 1), // 프론트는 1부터, 백은 0부터 → 보정
                    size,
                    Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
            );
            Board board = boardService.getBoardById(boardId);
            List<PostDTO> posts = boardService.getPostsByBoard(boardId, pageable);
            BoardDTO boardDto = new BoardDTO(board.getName(), posts);
            return ResponseEntity.ok(boardDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @PostMapping("/{boardId}/post")
    public ResponseEntity<Void> writePost(@PathVariable("boardId") int boardId,
                                          @RequestBody PostRequestDTO dto) {
        try {
            // 고려해야할것 첨부파일, 에디터, 스포일러 여부
            boardService.writePost(boardId, dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("/{boardId}/post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("boardId") int boardId, @PathVariable("postId") int postId) {
//        try {
//            // TODO : 게시글 상세 조회, 첨부파일도 고려
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
//        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardId}/post/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("boardId") int boardId, @PathVariable("postId") int postId) {
        try {
            // TODO : 게시글 수정, 첨부파일 꼭 고려
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @DeleteMapping("/{boardId}/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("boardId") int boardId, @PathVariable("postId") int postId) {
        try {
            // TODO : 게시글 삭제
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }
}
