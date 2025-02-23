package me.jeong.movieinfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON의 불필요한 필드를 무시
public class Movie extends BaseEntity {
    @Id
    private Long id;

    @Column(length = 1000)
    private String overview;

    @ElementCollection
    @CollectionTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre_id")
    private List<Integer> genre_ids;

    private String title;
    private String original_title;
    private String release_date;
    private String popularity;
    private double rating;
    private String backdrop_path;
    private String poster_path;

    // 배우와 다대다 관계
    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    public Movie(Long id, String overview, List<Integer> genre_ids, String title, String original_title, String release_date, String popularity, double rating, String backdrop_path, String poster_path) {
        this.id = id;
        this.overview = overview;
        this.genre_ids = genre_ids;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.popularity = popularity;
        this.rating = rating;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
    }
}

