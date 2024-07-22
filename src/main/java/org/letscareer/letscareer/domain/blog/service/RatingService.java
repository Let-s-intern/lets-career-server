package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetRatingsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {
    GetRatingsResponseDto getBlogRatings(Long blogId);
    void createBlogRating(Long blogId, CreateRatingRequestDto requestDto);
}
