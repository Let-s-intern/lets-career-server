package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.BlogRating;
import org.letscareer.letscareer.domain.blog.helper.BlogHelper;
import org.letscareer.letscareer.domain.blog.helper.RatingHelper;
import org.letscareer.letscareer.domain.blog.mapper.RatingMapper;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingHelper ratingHelper;
    private final BlogHelper blogHelper;
    private final RatingMapper ratingMapper;

    @Override
    public GetRatingsResponseDto getBlogRatings(Long blogId) {
        List<RatingDetailVo> ratingInfos = ratingHelper.findAllRatingDetailVos(blogId);
        return ratingMapper.toGetRatingsResponseDto(ratingInfos);
    }

    @Override
    public void createBlogRating(Long blogId, CreateRatingRequestDto requestDto) {
        Blog blog = blogHelper.findBlogByIdByOrThrow(blogId);
        BlogRating newRating = BlogRating.createBlogRating(blog, requestDto);
        ratingHelper.saveRating(newRating);
    }
}
