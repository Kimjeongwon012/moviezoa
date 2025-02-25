package me.jeong.movieinfo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private Long id;
    @JsonProperty("cast")
    private List<CastDTO> casts;
    @JsonProperty("crew")
    private List<CrewDTO> crews;
}
