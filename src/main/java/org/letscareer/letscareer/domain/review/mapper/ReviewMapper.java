package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeReviewStatusResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.*;
import org.letscareer.letscareer.domain.review.vo.*;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public ReviewVo toReviewVo(ReviewInfoVo reviewInfoVo, List<ReviewItemVo> reviewItemVos) {
        return ReviewVo.of(reviewInfoVo, reviewItemVos);
    }

    public <T> ReviewAdminVo toReviewAdminVo(T reviewInfo, List<ReviewItemAdminVo> reviewItemAdminVos) {
        return ReviewAdminVo.of(reviewInfo, reviewItemAdminVos);
    }

    public <T> ReviewMyVo toReviewMyVo(T reviewInfo, List<ReviewItemVo> reviewItemVos) {
        return ReviewMyVo.of(reviewInfo, reviewItemVos);
    }

    public GetReviewResponseDto toGetReviewResponseDto(List<ReviewVo> reviewVos, PageInfo pageInfo) {
        return GetReviewResponseDto.of(reviewVos, pageInfo);
    }

    public GetReviewForAdminResponseDto toGetReviewForAdminResponseDto(List<ReviewAdminVo> reviewAdminVos) {
        return GetReviewForAdminResponseDto.of(reviewAdminVos);
    }

    public GetMyReviewResponseDto toGetMyReviewResponseDto(ReviewMyVo reviewInfo) {
        return GetMyReviewResponseDto.of(reviewInfo);
    }

    public GetBlogReviewForAdminResponseDto toGetBlogReviewForAdminResponseDto(List<BlogReviewAdminVo> blogReviewAdminVos) {
        return GetBlogReviewForAdminResponseDto.of(blogReviewAdminVos);
    }

    public GetBlogReviewResponseDto toGetBlogReviewResponseDto(List<BlogReviewVo> blogReviewVos, PageInfo pageInfo) {
        return GetBlogReviewResponseDto.of(blogReviewVos, pageInfo);
    }

    public GetReviewCountResponseDto toGetReviewCountResponseDto(Long reviewCount, Long blogReviewCount) {
        return GetReviewCountResponseDto.of(reviewCount, blogReviewCount);
    }

    public GetChallengeReviewStatusResponseDto toGetChallengeReviewStatusResponseDto(Long reviewId) {
        return GetChallengeReviewStatusResponseDto.of(reviewId);
    }
}
