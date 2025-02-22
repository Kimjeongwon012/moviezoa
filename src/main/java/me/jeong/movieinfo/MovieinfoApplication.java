package me.jeong.movieinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MovieinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieinfoApplication.class, args);
    }

}
