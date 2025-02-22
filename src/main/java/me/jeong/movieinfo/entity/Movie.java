package me.jeong.movieinfo.entity;

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
public class Movie extends BaseEntity {
    @Id
    private Long id;

    @Column(length = 1000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre_id")
    private List<Integer> genre;

    private String title;
    private String originalTitle; // 원제
    private String releaseDate;
    private String image_url;
    private Boolean adult; // 성인 영화 여부
    private double rating;
    private String posterUrl;

    // 배우와 다대다 관계
    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    public Movie(Long id, String description, List<Integer> genre, String title, String originalTitle, String releaseDate, Boolean adult, String posterUrl) {
        this.id = id;
        this.description = description;
        this.genre = genre;
        this.title = title;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.posterUrl = posterUrl;
    }


}

