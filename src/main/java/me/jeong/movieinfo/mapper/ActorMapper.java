package me.jeong.movieinfo.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.entity.Actor;

import java.util.Map;

public class ActorMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Actor mapToActor(Map<String, Object> actorData) {
        return objectMapper.convertValue(actorData, Actor.class);
    }
}
