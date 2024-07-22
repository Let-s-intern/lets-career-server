package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {
    void createBlogRating(Long blogId, CreateRatingRequestDto requestDto);
}
