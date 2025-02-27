package me.jeong.movieinfo.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDTO {
    @Transient // JPA 관리 대상 제외
    private final String base_image_url;

    public CrewDTO() {
        this.base_image_url = Movie_Constants.BaseImageUrl;
    }

    private Long id;
    private String name;
    private String job;
    private String department;

    @JsonProperty("profile_path")
    private String profilePath;

    public String getProfilePath() {
        return base_image_url + profilePath;
    }
}
