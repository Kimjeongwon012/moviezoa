package me.jeong.movieinfo;

import me.jeong.movieinfo.utils.DateUtils;
import me.jeong.movieinfo.external.TmdbAPI;
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
    }

}
