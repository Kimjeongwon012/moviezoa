package me.jeong.movieinfo.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.domain.Genre;
import me.jeong.movieinfo.utils.Movie_Constants;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON의 불필요한 필드를 무시
public class MovieDTO {
    @Transient // JPA 관리 대상 제외
    private final String base_image_url;

    public MovieDTO() {
        this.base_image_url = Movie_Constants.BaseImageUrl;
    }

    private Long id;
    private String overview;
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("release_date")
    private String releaseDate;
    private double popularity;
    private double rating;
    private Long runtime;
    private String status;
    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("poster_path")
    private String posterPath;

    private List<GenreDTO> genres;
    private ImageDTO images;
    private VideoDTO videos;
    private CreditDTO credits;

    public String getBackdropPath() {
        return base_image_url + backdropPath;
    }

    public String getPosterPath() {
        return base_image_url + posterPath;
    }
}


