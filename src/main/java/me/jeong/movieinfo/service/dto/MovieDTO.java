package me.jeong.movieinfo.service.dto;

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
public class MovieDTO {
    @Transient // JPA 관리 대상 제외
    private final String base_image_url;

    public MovieDTO() {
        this.base_image_url = Movie_Constants.BaseImageUrl;
        System.out.println(base_image_url);
    }

    private Long id;
    private String overview;
    private String title;
    private List<GenreDTO> genres;
    private String original_title;
    private String release_date;
    private double popularity;
    private double rating;
    private String backdrop_path;
    private String poster_path;
    private List<CastDTO> cast;
    private List<CrewDTO> crew;

    public String getBackdrop_path() {
        return base_image_url + backdrop_path;
    }

    public String getPoster_path() {
        return base_image_url + poster_path;
    }
}


