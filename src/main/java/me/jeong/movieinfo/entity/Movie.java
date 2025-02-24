package me.jeong.movieinfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.component.Movie_Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON의 불필요한 필드를 무시
public class Movie extends BaseEntity {
    @Transient // JPA 관리 대상 제외
    private final String base_image_url;

    public Movie() {
        this.base_image_url = Movie_Constants.BaseImageUrl;
        System.out.println(base_image_url);
    }

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
    private double popularity;
    private double rating;
    private String backdrop_path;
    private String poster_path;

    public String getBackdrop_path() {
        return base_image_url + backdrop_path;
    }

    public String getPoster_path() {
        return base_image_url + poster_path;
    }


    // 배우와 다대다 관계
    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();
}

