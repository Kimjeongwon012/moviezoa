package me.jeong.movieinfo;

import lombok.extern.java.Log;
import me.jeong.movieinfo.component.DateUtils;
import me.jeong.movieinfo.service.TmdbAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieinfoApplicationTests {

    @Autowired
    private TmdbAPI tmdbAPI;
    private DateUtils date;

    @Test
    void contextLoads() {
        tmdbAPI.fetchAndStoreMovies();
    }

}
