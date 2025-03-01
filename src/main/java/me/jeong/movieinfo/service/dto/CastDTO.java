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
public class CastDTO {
    @Transient // JPA 관리 대상 제외
    private final String base_image_url;

    public CastDTO() {
        this.base_image_url = Movie_Constants.BaseImageUrl;
    }

    private Long id;
    private String name;
    private String character;
    private int gender;

    @JsonProperty("profile_path")
    private String profilePath;
    private int order;

    public String getProfilePath() {
        if (profilePath == null) {
            if (gender == 2) {
                return "/api/actors/default-profile/2";
            } else if (gender == 1) {
                return "/api/actors/default-profile/1";
            } else {
                return "/api/actors/default-profile/1";
            }
        }
        return base_image_url + profilePath;
    }
}
