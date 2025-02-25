package me.jeong.movieinfo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON의 불필요한 필드를 무시
public class Actor extends BaseEntity {
    @Id
    private Long id;

    private String adult;

    private String gender;

    private String known_for_department;

    private String name;

    private String popularity;

    private String profile_path;
}
