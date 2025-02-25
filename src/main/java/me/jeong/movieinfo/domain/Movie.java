package me.jeong.movieinfo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.utils.Movie_Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON의 불필요한 필드를 무시
public class Movie extends BaseEntity {
    @Id
    private Long id;

    @Column(length = 1000)
    private String overview;

    private String title;
    private String original_title;
    private String release_date;
    private double popularity;
    private double rating;
    private String backdrop_path;
    private String poster_path;
}

