package me.jeong.movieinfo.service;

import me.jeong.movieinfo.service.TmdbAPI;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MovieScheduler {

    private final TmdbAPI tmdbapi;

    public MovieScheduler(TmdbAPI tmdbapi) {
        this.tmdbapi = tmdbapi;
    }

    @Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시 실행
    public String syncMovies() {
        String data = "fail";
        System.out.println("TMDb API에서 최신 영화 정보 가져오기...");
        return tmdbapi.getMovies();
    }
}
