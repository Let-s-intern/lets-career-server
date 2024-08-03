package org.letscareer.letscareer.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetBlogRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.BlogRating;
import org.letscareer.letscareer.domain.blog.helper.BlogHelper;
import org.letscareer.letscareer.domain.blog.helper.RatingHelper;
import org.letscareer.letscareer.domain.blog.mapper.RatingMapper;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.letscareer.letscareer.domain.blog.vo.RatingVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingHelper ratingHelper;
    private final BlogHelper blogHelper;
    private final RatingMapper ratingMapper;

    @Override
    public GetRatingsResponseDto getRatings(Pageable pageable) {
        Page<RatingVo> ratingVos = ratingHelper.findRatingVos(pageable);
        return ratingMapper.toGetRatingsResponseDto(ratingVos);
    }

    @Override
    public GetBlogRatingsResponseDto getBlogRatings(Long blogId) {
        List<RatingDetailVo> ratingInfos = ratingHelper.findAllRatingDetailVos(blogId);
        return ratingMapper.toGetBlogRatingsResponseDto(ratingInfos);
    }

    @Override
    public void createBlogRating(Long blogId, CreateRatingRequestDto requestDto) {
        Blog blog = blogHelper.findBlogByIdByOrThrow(blogId);
        BlogRating newRating = BlogRating.createBlogRating(blog, requestDto);
        ratingHelper.saveRating(newRating);
    }
}
