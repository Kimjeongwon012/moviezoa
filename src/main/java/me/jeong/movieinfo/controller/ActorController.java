package me.jeong.movieinfo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/default-profile/{gender}")
    public ResponseEntity<byte[]> getDefaultProfileImage(@PathVariable("gender") int gender) {
        String url = "";
        if (gender == 2) {
            url = "/img/default-profileM.jpg";
        } else {
            url = "/img/default-profileW.jpg";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpg");
        try {
            Resource resource = new ClassPathResource("static" + url);
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
            log.info(resource.getURL().toString());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(null, headers, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
