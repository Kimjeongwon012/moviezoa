package me.jeong.movieinfo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.domain.Board;
import me.jeong.movieinfo.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private long id;
    private User user;
    private String title;
    private String category;
    private Integer views;
    private Integer likes;
    private Boolean isSpoiler;
    private LocalDateTime updatedAt;
}
