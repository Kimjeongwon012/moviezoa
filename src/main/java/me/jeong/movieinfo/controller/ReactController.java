package me.jeong.movieinfo.controller;

import java.util.Date;

import me.jeong.movieinfo.external.TmdbAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReactController {
    private static final Logger log = LoggerFactory.getLogger(ReactController.class);

    private final TmdbAPI tmdbapi;

    public ReactController(TmdbAPI tmdbapi) {
        this.tmdbapi = tmdbapi;
    }

    @GetMapping("/api")
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";
    }

    @GetMapping("/api/test")
    public String test() {
        return "";
    }

}