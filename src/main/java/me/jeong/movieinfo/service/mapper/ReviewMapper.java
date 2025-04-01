package me.jeong.movieinfo.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jeong.movieinfo.domain.Review;
import me.jeong.movieinfo.service.dto.MovieReviewDTO;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {
    public static MovieReviewDTO toDto(Review review) {
        if (review == null) {
            return null;
        }

        MovieReviewDTO dto = new MovieReviewDTO();

        dto.setId(review.getId());
        dto.setContent(review.getContent());
        dto.setRating((review.getRating()));

        return dto;
    }

    public static List<MovieReviewDTO> toDtoList(List<Review> reviews) {
        if (reviews == null) {
            return null;
        }

        List<MovieReviewDTO> dtoList = new ArrayList<>();
        for (Review Review : reviews) {
            dtoList.add(toDto(Review));
        }
        return dtoList;
    }

}
