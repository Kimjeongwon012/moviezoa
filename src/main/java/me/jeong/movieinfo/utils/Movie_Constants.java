package me.jeong.movieinfo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Movie_Constants {

    @Value("${base.image.url}")
    private String baseImageUrl; // @Value는 일반 필드에만 주입 가능

    public static String BaseImageUrl; // 정적 필드

    @PostConstruct
    public void init() {
        BaseImageUrl = baseImageUrl; // @Value가 주입된 후 정적 필드에 값 할당
    }
}
