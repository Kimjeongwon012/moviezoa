package me.jeong.movieinfo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieVideoDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("iso_639_1")
    private String languageCode;

    @JsonProperty("iso_3166_1")
    private String countryCode;

    @JsonProperty("name")
    private String title;

    @JsonProperty("key")
    private String videoKey;

    @JsonProperty("site")
    private String platform;

    @JsonProperty("size")
    private int resolution;

    @JsonProperty("type")
    private String category;

    @JsonProperty("official")
    private boolean isOfficial;

    @JsonProperty("published_at")
    private String publishedAt;
}
