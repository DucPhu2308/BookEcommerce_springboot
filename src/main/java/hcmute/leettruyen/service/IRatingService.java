package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.RatingDto;
import hcmute.leettruyen.response.RatingResponse;

import java.util.List;

public interface IRatingService {
    RatingResponse createRating(RatingDto ratingDto) throws Exception;
    RatingResponse updateRating(Integer id, RatingDto ratingDto) throws Exception;
    void deleteRating(Integer id) throws Exception;
    List<RatingResponse> getRatingByBook(Integer id) throws Exception;
    void setRating(Integer id) throws Exception;
}
