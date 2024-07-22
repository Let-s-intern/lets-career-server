package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.BlogRating;
import org.letscareer.letscareer.domain.blog.helper.RatingHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingHelper ratingHelper;
    // private final BlogHelper blogHelper;

    @Override
    public void createBlogRating(Long blogId, CreateRatingRequestDto requestDto) {
        Blog blog = null;   // blogHelper.findBlogByBlogId(blogId);
        BlogRating newRating = BlogRating.createBlogRating(blog, requestDto);
        ratingHelper.saveRating(newRating);
    }
}
