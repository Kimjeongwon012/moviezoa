package me.jeong.movieinfo.config;

import jakarta.annotation.PostConstruct;
import me.jeong.movieinfo.external.TmdbAPI;
import org.springframework.stereotype.Component;

@Component
public class Startup {

    private final TmdbAPI tmdbapi;

    public Startup(TmdbAPI tmdbapi) {
        this.tmdbapi = tmdbapi;
    }

    @PostConstruct
    public void init() {
        // 서버 시작시 너무 많은 부하
//        tmdbapi.fetchAndStoreMovies();
    }
}
