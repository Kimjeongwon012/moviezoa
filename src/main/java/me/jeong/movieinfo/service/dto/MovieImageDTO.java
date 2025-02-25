package me.jeong.movieinfo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.utils.Movie_Constants;

@Getter
@Setter
@AllArgsConstructor
public class MovieImageDTO {
    @Transient // JPA 관리 대상 제외
    private final String base_image_url;

    public MovieImageDTO() {
        this.base_image_url = Movie_Constants.BaseImageUrl;
    }

    @JsonProperty("aspect_ratio")
    private double aspectRatio;

    private int height;

    private int width;

    @JsonProperty("iso_639_1")
    private String languageCode;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    public String getFilePath() {
        return base_image_url + filePath;
    }
}
