package me.jeong.movieinfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jeong.movieinfo.entity.Movie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
