package me.jeong.movieinfo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long boardId;
    private Long userId;
    private String title;
    private String category;
    private Integer views;
    private Integer likes;
    private Boolean isSpoiler;
}
