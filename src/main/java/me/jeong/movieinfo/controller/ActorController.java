package me.jeong.movieinfo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/default-profile/{gender}")
    public String getDefaultProfileImage(@PathVariable("gender") int gender) {
        log.info("1ss");
        if (gender == 2) {
            return "/img/default-profileM.png";
        } else if (gender == 1) {
            return "/img/default-profileW.png";
        } else {
            return "/img/default-profileM.png";
        }
    }
}
