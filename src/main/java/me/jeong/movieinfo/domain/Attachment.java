package me.jeong.movieinfo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;
    private long postId;
    private String filename;
    private String filepath;
    private long filesize;
    private String filetype;
}
