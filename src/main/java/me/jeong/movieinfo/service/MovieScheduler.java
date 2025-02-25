package me.jeong.movieinfo.service;

import me.jeong.movieinfo.external.TmdbAPI;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MovieScheduler {

    private final TmdbAPI tmdbapi;

    public MovieScheduler(TmdbAPI tmdbapi) {
        this.tmdbapi = tmdbapi;
    }

    @Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시 실행
    public void syncMovies() {
        tmdbapi.fetchAndStoreMovies();
    }
}
