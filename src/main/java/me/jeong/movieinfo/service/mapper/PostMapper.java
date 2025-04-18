package me.jeong.movieinfo.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.domain.Post;
import me.jeong.movieinfo.service.dto.PostDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static PostDTO mapToPostDTO(String PostData) throws JsonProcessingException {
        return objectMapper.readValue(PostData, PostDTO.class);
    }

    public static Post mapToPost(Map<String, Object> PostData) {
        return objectMapper.convertValue(PostData, Post.class);
    }

    public static PostDTO toDto(Post Post) {
        if (Post == null) {
            return null;
        }

        PostDTO dto = new PostDTO();

        dto.setId(Post.getId());
        dto.setUser(Post.getUser());
        dto.setTitle(Post.getTitle());
        dto.setCategory(Post.getCategory());
        dto.setViews(Post.getViews());
        dto.setLikes(Post.getLikes());
        dto.setIsSpoiler(Post.getIsSpoiler());
        dto.setUpdatedAt(Post.getUpdatedAt());

        return dto;
    }

    public static List<PostDTO> toDtoList(List<Post> Posts) {
        if (Posts == null) {
            return null;
        }

        List<PostDTO> dtoList = new ArrayList<>();
        for (Post Post : Posts) {
            dtoList.add(toDto(Post));
        }
        return dtoList;
    }

}
