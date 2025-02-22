package me.jeong.movieinfo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.entity.Movie;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Actor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String birthDate;

    private String nationality;

    private String profileUrl;

    private String gender;

    private String filmographyUrl; // 필모그래피 페이지 링크 (예: IMDB, Naver Movie)

    private Long representativeMovieId; // 대표 작품 (대표 영화 ID)
}
