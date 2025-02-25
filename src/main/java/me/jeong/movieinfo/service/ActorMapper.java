package me.jeong.movieinfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.domain.Actor;

import java.util.Map;

public class ActorMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Actor mapToActor(Map<String, Object> actorData) {
        return objectMapper.convertValue(actorData, Actor.class);
    }
}
